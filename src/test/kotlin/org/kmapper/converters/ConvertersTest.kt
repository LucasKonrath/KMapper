package org.kmapper.converters

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConvertersTest {

    private val converters = Converters()

    @Test
    fun testCustomConversion() {
        val from = "TEST_VALUE"

        converters.addConverter(String::class, TestConversionEnum::class) { from ->
            from as String
            TestConversionEnum.valueOf(from)
        }

        val to = converters.getConverter(String::class, TestConversionEnum::class)?.invoke(from)!!

        Assertions.assertEquals(TestConversionEnum.TEST_VALUE, to)

    }

    enum class TestConversionEnum() {
        TEST_VALUE
    }

}