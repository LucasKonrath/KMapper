package mapper

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER)
@Repeatable
/**
 * Annotation used to mark the destination field and class of the property to be converted.
 */
annotation class MapperModelField(val destinationField: String, val destinationClass: String)
