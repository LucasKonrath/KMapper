package mapper

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KType
import kotlin.reflect.full.*

class MapperModel {

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

        to::class.declaredMemberProperties
            .forEach { m ->
                val ann: MapperModelField? =
                    m.findAnnotations<MapperModelField>()
                        .firstOrNull { ann -> ann.destinationClass == cls.simpleName }
                val nameToPut = ann?.destinationField ?: m.name
                mapOfProps[nameToPut] = m.getter.call(to)

            }

        if (emptyConstructor == true) {
            newObject = cls.createInstance()
        } else {

            val args =
                cls.primaryConstructor?.parameters?.map { param -> convert(mapOfProps[param.name]!!, param.type) }
                    ?.toTypedArray()
                    .orEmpty()

            newObject = cls.primaryConstructor?.call(*args)
        }

        cls.declaredMemberProperties.forEach { m ->
            if (m is KMutableProperty<*>) {
                m.setter.call(
                    newObject,
                    convert(mapOfProps[m.name]!!, m.returnType)
                )
            }
        }

        return newObject!!
    }

    private fun convert(from: Any, to: KType): Any {
        val typeClassifier = from::class.createType().classifier
        val toClassifier = to.classifier
        val converters = Converters()
        return converters.getConverter(typeClassifier, toClassifier)?.invoke(from)!!
    }
}