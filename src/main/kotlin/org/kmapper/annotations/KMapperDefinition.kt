package me.tatocaster.annotation

import kotlin.reflect.KClass

/**
 * Annotation used to mark the presence of a mapping method. Interface should extend the KMapper interface.
 */
annotation class KMapperDefinition(val originalClass: KClass<*>, val destination: KClass<*>)
