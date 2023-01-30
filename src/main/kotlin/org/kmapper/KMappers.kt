package org.kmapper

import org.kmapper.testClasses.DestinationRecordClass
import org.kmapper.testClasses.EmptyConstructorClass
import org.kmapper.testClasses.OriginalAnnotatedClass
import org.kmapper.testClasses.OriginalClass
import kotlin.reflect.KClass

@org.kmapper.annotations.KMapperDefinitions
class KMappers {
    val converters = mutableListOf<Pair<KClass<*>, KClass<*>>>()
    init {
        Pair(OriginalClass::class, DestinationRecordClass::class)
        Pair(OriginalClass::class, EmptyConstructorClass::class)
        Pair(OriginalAnnotatedClass::class, DestinationRecordClass::class)
        Pair(OriginalAnnotatedClass::class, EmptyConstructorClass::class)
    }
}