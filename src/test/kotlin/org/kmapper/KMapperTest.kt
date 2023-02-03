package org.kmapper

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.kmapper.testClasses.DestinationRecordClass
import org.kmapper.testClasses.EmptyConstructorClass
import org.kmapper.testClasses.OriginalAnnotatedClass
import org.kmapper.testClasses.OriginalClass
import kotlin.system.measureNanoTime
import kotlin.system.measureTimeMillis
import kotlin.time.measureTime

class KMapperTest {

    private val kMapper = KMapper()

    @Test
    fun testMappingEmptyConstructorWithoutAnnotations() {
        val origin = getMock()

        val time = measureTimeMillis {
            val emptyConstructorClass = kMapper.map(origin, EmptyConstructorClass::class)
        }
        println("Non-cache time: $time")

        val time2 = measureTimeMillis {
            val secondOne =  kMapper.map(origin, EmptyConstructorClass::class)
        }

        println("Time cached: $time2")
    }

    @Test
    fun testMappingEmptyConstructorWithAnnotations() {
        val origin = getMockAnnotated()
        val emptyConstructorClass = kMapper.map(origin, EmptyConstructorClass::class)
        assertEquals(origin.string, emptyConstructorClass.testString)
        assertEquals(origin.int, emptyConstructorClass.testInt)
    }

    @Test
    fun testMappingFullConstructorWithoutAnnotations() {
        val origin = OriginalClass(testString = "Test String", testInt = 4444)
        val destination = kMapper.map(origin, DestinationRecordClass::class)

        assertEquals(origin.testString, destination.testString)
        assertEquals(origin.testInt, destination.testInt)
    }

    @Test
    fun testMappingFullConstructorWithAnnotations() {
        val origin = getMockAnnotated()
        val destination = kMapper.map(origin, DestinationRecordClass::class)

        assertEquals(origin.string, destination.testString)
        assertEquals(origin.int, destination.testInt)
    }

    fun getMock(): OriginalClass {
        return OriginalClass(testString = "Test String", testInt = 4444)
    }

    fun getMockAnnotated(): OriginalAnnotatedClass {
        return OriginalAnnotatedClass(string = "Test String", int = 4444)
    }

}