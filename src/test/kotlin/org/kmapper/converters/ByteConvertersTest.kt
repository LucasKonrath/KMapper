package org.kmapper.converters

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ByteConvertersTest {

    private val converters = Converters()

    @Test
    fun convertByteToString() {
        val from = 0XE0.toByte()
        val to: Any = converters.getConverter(from::class, String::class)?.invoke(from)!!
        Assertions.assertEquals(from.toString(), to)
    }

    @Test
    fun convertByteToInt() {
        val from = 0XE0.toByte()
        val to: Any = converters.getConverter(from::class, Int::class)?.invoke(from)!!
        Assertions.assertEquals(from.toInt(), to)
    }

    @Test
    fun convertByteToDouble() {
        val from = 0XE0.toByte()
        val to: Any = converters.getConverter(from::class, Double::class)?.invoke(from)!!
        Assertions.assertEquals(from.toDouble(), to)
    }

    @Test
    fun convertByteToByte() {
        val from = 0XE0.toByte()
        val to: Any = converters.getConverter(from::class, Byte::class)?.invoke(from)!!
        Assertions.assertEquals(from.toByte(), to)
    }

    @Test
    fun convertByteToShort() {
        val from = 0XE0.toByte()
        val to: Any = converters.getConverter(from::class, Short::class)?.invoke(from)!!
        Assertions.assertEquals(from.toShort(), to)
    }

}