package org.kmapper.testClasses

import org.kmapper.KMappedField

data class OriginalAnnotatedClass(
        @property:KMappedField(destinationField = "testString")
        @property:KMappedField(destinationField = "testString")
        val string: String,
        @property:KMappedField(destinationField = "testInt")
        @property:KMappedField(destinationField = "testInt")
        val int: Int
    )