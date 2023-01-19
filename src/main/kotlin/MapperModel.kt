import kotlin.reflect.KClass
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.createInstance
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation

class MapperModel {

    fun <T : Any> map(to: Any, cls: KClass<T>): T  {

        val newObject = cls.createInstance()
        val mapOfProps = mutableMapOf<String?, Any?>()

        to::class.declaredMemberProperties
            .forEach {m ->

                val ann = m.findAnnotation<MapperModelField>()
                val nameToPut = ann?.destinationField ?: m.name
                mapOfProps[nameToPut] = m.getter.call(to)

            }

        cls.declaredMemberProperties.forEach{
            m ->
                // If is val, then it replaces via reflection.
                if(m is KMutableProperty<*>){
                    m.setter.call(newObject, mapOfProps[m.name])
                }
        }

        println("New Object: $newObject")

        return newObject
    }
}