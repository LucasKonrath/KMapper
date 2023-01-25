package org.kmapper

import org.kmapper.cache.TimedCache
import org.kmapper.converters.Converters
import kotlin.reflect.*
import kotlin.reflect.full.*

class KMapper {

    val converters = Converters()
    val cache = TimedCache()

    /**
     * Function called to convert an instance of an object to an instance of the specified KClass.
     * It'll get the origin class, insert all of its member properties onto a map with their corresponding name
     * (if MapperModelField is present, it'll get the destinationField value, else it'll get the property name).
     * Then the method will instantiate the destination object with the biggest constructor available, and will populate
     * the object with the values from the original object. The remaining member properties will be set via Reflection.
     *
     * For inserting each value on the destination class, a Converter is used. Converters are used to convert between
     * two data types, and they are registered on the Converters.kt file. If you wish to expand the current converters,
     * or create custom ones, you can add them on that file.
     */
    fun <T : Any> map(to: Any, cls: KClass<T>): T {

        val emptyConstructor: Boolean? = cls.primaryConstructor?.parameters?.isEmpty()

        var newObject: T? = null
        val mapOfProps = mutableMapOf<String?, Any?>()

        getDeclaredMemberProperties(to::class)
            .forEach { m ->
                val ann: KMappedField? =
                    m.findAnnotation<KMappedField>()
                val nameToPut = ann?.destinationField ?: m.name
                mapOfProps[nameToPut] = m.getter.call(to)
            }

        if (emptyConstructor == true) {
            newObject = cls.createInstance()
        } else {

            val args =
                getPrimaryConstructor(cls)?.parameters?.map { param -> convert(mapOfProps[param.name]!!, param.type) }
                    ?.toTypedArray()
                    .orEmpty()

            newObject = getPrimaryConstructor(cls)?.call(*args) as T?
        }

        getDeclaredMemberProperties(cls).forEach { m ->
            if (m is KMutableProperty<*>) {
                m.setter.call(
                    newObject,
                    convert(mapOfProps[m.name]!!, m.returnType)
                )
            }
        }

        return newObject!!
    }

    fun getDeclaredMemberProperties(clazz: KClass<*>): Collection<KProperty1<*, *>> {
         val cached = cache.get(clazz.simpleName!!)
         if(cached != null){
            return cached as Collection<KProperty1<*, *>>
         } else {
            val toReturn = clazz.declaredMemberProperties
            cache.put(clazz.simpleName!!, toReturn)
            cache.get("OriginalClass")
            return toReturn
         }
    }

    fun getPrimaryConstructor(clazz: KClass<*>): KFunction<Any>? {
        val cached = cache.get(clazz.simpleName!!+"Constructor")
        if(cached != null){
            return cached as KFunction<Any>
        } else {
            val toReturn = clazz.primaryConstructor
            if (toReturn != null) {
                cache.put(clazz.simpleName!!+"Constructor", toReturn)
            }
            return toReturn
        }
    }
    private fun convert(from: Any, to: KType): Any {
        val typeClassifier = from::class.createType().classifier
        val toClassifier = to.classifier
        return converters.getConverter(typeClassifier, toClassifier)?.invoke(from)!!
    }
}