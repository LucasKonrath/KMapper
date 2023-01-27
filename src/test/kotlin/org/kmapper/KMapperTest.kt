package org.kmapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis

class KMapperTest {

    private val kMapper = KMapper()

    @RepeatedTest(10)
    fun testMappingEmptyConstructorWithoutAnnotations() {

        val origin = getMock()

        val time = measureNanoTime{
            val emptyConstructorClass = kMapper.map(origin, EmptyConstructorClass::class)
        }

        val originalTime = measureNanoTime {
            val empty = EmptyConstructorClass()
            empty.testInt = origin.testInt
            empty.testString = origin.testString
         }

         println("Reflection $time")
         println("Original $originalTime")

        val cachedTime = measureNanoTime {
            val emptyConstructorClass = kMapper.map(origin, EmptyConstructorClass::class)
        }

        println("Cached time $cachedTime")

    }

    @RepeatedTest(10)
    fun testMappingEmptyConstructorWithAnnotations() {
        val origin = getMockAnnotated()
        val emptyConstructorClass = kMapper.map(origin, EmptyConstructorClass::class)
        assertEquals(origin.string, emptyConstructorClass.testString)
        assertEquals(origin.int, emptyConstructorClass.testInt)
    }

    @RepeatedTest(10)
    fun testMappingFullConstructorWithoutAnnotations() {
        val origin = OriginalClass(testString = "Test String", testInt = 4444)
        val destination = kMapper.map(origin, DestinationRecordClass::class)

        assertEquals(origin.testString, destination.testString)
        assertEquals(origin.testInt, destination.testInt)
    }

    @RepeatedTest(10)
    fun testMappingFullConstructorWithAnnotations() {
        val origin = getMockAnnotated()
        val destination = kMapper.map(origin, DestinationRecordClass::class)

        assertEquals(origin.string, destination.testString)
        assertEquals(origin.int, destination.testInt)
    }


    @Test
    fun testConvertStringToPair() {

        val obj = StringClass("First, Second")

        kMapper.converters.addConverter(
            String::class,
            Pair::class
        ){
            from ->
                from as String
                val first = from.split(",").first()
                val second = from.split(",")[1]
                Pair(first, second)
        }

        val to: PairClass = kMapper.map(obj, cls = PairClass::class)
        println("$to")
    }

    fun getMock(): OriginalClass {
        return OriginalClass(testString = "Test String", testInt = 4444)
    }

    data class OriginalClass(
        val testString: String,
        val testInt: Int
    )

    fun getMockAnnotated(): OriginalAnnotatedClass {
        return OriginalAnnotatedClass(string = "Test String", int = 4444)
    }

    data class OriginalAnnotatedClass(
        @property:KMappedField(destinationField = "testString")
        val string: String,
        @property:KMappedField(destinationField = "testInt")
        val int: Int
    )

    class EmptyConstructorClass {
        var testString: String? = null
        var testInt: Int? = null
    }

    data class DestinationRecordClass(val testString: String, val testInt: Int)

    data class StringClass(val value: String)

    data class PairClass(val value: Pair<*, *>)
}