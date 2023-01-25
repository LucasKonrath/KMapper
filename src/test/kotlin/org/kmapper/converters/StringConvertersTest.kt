package org.kmapper.converters

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class StringConvertersTest {

    private val converters = Converters()

    @Test
    fun convertStringToString() {
        val from = "Test"
        val to: Any = converters.getConverter(from::class, String::class)?.invoke(from)!!
        Assertions.assertEquals(from, to)
    }

    @Test
    fun convertStringToInt() {
        val from = "10"
        val to: Any = converters.getConverter(from::class, Int::class)?.invoke(from)!!
        Assertions.assertEquals(from.toInt(), to)
    }

    @Test
    fun convertStringToDouble() {
        val from = "10.0"
        val to: Any = converters.getConverter(from::class, Double::class)?.invoke(from)!!
        Assertions.assertEquals(from.toDouble(), to)
    }

    @Test
    fun convertStringToByte() {
        val from = "10"
        val to: Any = converters.getConverter(from::class, Byte::class)?.invoke(from)!!
        Assertions.assertEquals(from.toByte(), to)
    }

    @Test
    fun convertStringToShort() {
        val from = "10"
        val to: Any = converters.getConverter(from::class, Short::class)?.invoke(from)!!
        Assertions.assertEquals(from.toShort(), to)
    }
}