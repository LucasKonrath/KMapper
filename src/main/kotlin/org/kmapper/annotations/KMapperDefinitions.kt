package org.kmapper.annotations

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS)
@Repeatable
/**
 * Annotation used to mark the destination field and class of the property to be converted.
 */
annotation class KMapperDefinitions()
