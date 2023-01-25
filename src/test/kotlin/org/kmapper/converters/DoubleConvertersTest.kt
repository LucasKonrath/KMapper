package org.kmapper.converters

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class DoubleConvertersTest {

    private val converters = Converters()

    @Test
    fun convertDoubleToString() {
        val from = 10.0
        val to: Any = converters.getConverter(from::class, String::class)?.invoke(from)!!
        Assertions.assertEquals(from.toString(), to)
    }

    @Test
    fun convertDoubleToInt() {
        val from = 10.0
        val to: Any = converters.getConverter(from::class, Int::class)?.invoke(from)!!
        Assertions.assertEquals(from.toInt(), to)
    }

    @Test
    fun convertDoubleToDouble() {
        val from = 10.0
        val to: Any = converters.getConverter(from::class, Double::class)?.invoke(from)!!
        Assertions.assertEquals(from, to)
    }

    @Test
    fun convertDoubleToByte() {
        val from = 10.0
        val to: Any = converters.getConverter(from::class, Byte::class)?.invoke(from)!!
        Assertions.assertEquals(from.toInt().toByte(), to)
    }

    @Test
    fun convertDoubleToShort() {
        val from = 10.0
        val to: Any = converters.getConverter(from::class, Short::class)?.invoke(from)!!
        Assertions.assertEquals(from.toInt().toShort(), to)
    }

}