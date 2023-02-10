package org.kmapper

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY, AnnotationTarget.PROPERTY_GETTER)
@Repeatable
/**
 * Annotation used to mark the destination field of the property to be converted.
 */
annotation class KMappedField(val destinationField: String)
