package org.kmapper.testClasses

import org.kmapper.KMappedField

data class OriginClass(
@KMappedField(destinationField = "testInt")
val int:Int,
@KMappedField(destinationField = "testString")
val string:String,
val double: Double,
val short: Short,
val byte: Byte)
