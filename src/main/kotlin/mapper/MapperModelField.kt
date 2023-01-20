package mapper

import kotlin.reflect.KClass
import kotlin.reflect.KType

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER)
@Repeatable
annotation class MapperModelField(val destinationField: String, val destinationClass: String)
