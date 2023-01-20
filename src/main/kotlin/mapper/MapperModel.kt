package mapper

import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KType
import kotlin.reflect.full.*

class MapperModel {

    fun <T : Any> map(to: Any, cls: KClass<T>): T {

        val emptyConstructor: Boolean? = cls.primaryConstructor?.parameters?.isEmpty()

        var newObject: T? = null
        val mapOfProps = mutableMapOf<String?, Any?>()

        to::class.declaredMemberProperties
            .forEach { m ->
                val ann = m.findAnnotations<MapperModelField>().filter { ann -> ann.destinationClass == cls.simpleName }
                    .first()
                val nameToPut = ann.destinationField
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

    fun convert(from: Any, to: KType): Any {
        val typeClassifier = from::class.createType().classifier
        val toClassifier = to.classifier
        val converters = Converters()
        return converters.getConverter(typeClassifier, toClassifier)?.invoke(from)!!
    }
}