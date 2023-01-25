package org.kmapper.converters

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ShortConvertersTest {

    private val converters = Converters()

    @Test
    fun convertShortToString() {
        val from = 0XE0.toShort()
        val to: Any = converters.getConverter(from::class, String::class)?.invoke(from)!!
        Assertions.assertEquals(from.toString(), to)
    }

    @Test
    fun convertShortToInt() {
        val from = 0XE0.toShort()
        val to: Any = converters.getConverter(from::class, Int::class)?.invoke(from)!!
        Assertions.assertEquals(from.toInt(), to)
    }

    @Test
    fun convertShortToDouble() {
        val from = 0XE0.toShort()
        val to: Any = converters.getConverter(from::class, Double::class)?.invoke(from)!!
        Assertions.assertEquals(from.toDouble(), to)
    }

    @Test
    fun convertShortToByte() {
        val from = 0XE0.toShort()
        val to: Any = converters.getConverter(from::class, Byte::class)?.invoke(from)!!
        Assertions.assertEquals(from.toByte(), to)
    }

    @Test
    fun convertShortToShort() {
        val from = 0XE0.toShort()
        val to: Any = converters.getConverter(from::class, Short::class)?.invoke(from)!!
        Assertions.assertEquals(from, to)
    }

}