package org.kmapper.testClasses

import org.kmapper.KMappedField

data class OriginalAnnotatedClass(
        @property:KMappedField(destinationField = "testString", destinationClass = "EmptyConstructorClass")
        @property:KMappedField(destinationField = "testString", destinationClass = "DestinationRecordClass")
        val string: String,
        @property:KMappedField(destinationField = "testInt", destinationClass = "EmptyConstructorClass")
        @property:KMappedField(destinationField = "testInt", destinationClass = "DestinationRecordClass")
        val int: Int
    )