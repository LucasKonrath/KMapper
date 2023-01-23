import mapper.MapperModel
import mapper.MapperModelField
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MapperModelTest {

    private val mapperModel = MapperModel()

    @Test
    fun testMappingEmptyConstructorWithoutAnnotations() {
        val origin = getMock()
        val emptyConstructorClass = mapperModel.map(origin, EmptyConstructorClass::class)

        assertEquals(origin.testString, emptyConstructorClass.testString)
        assertEquals(origin.testInt, emptyConstructorClass.testInt)
    }

    @Test
    fun testMappingEmptyConstructorWithAnnotations() {
        val origin = getMockAnnotated()
        val emptyConstructorClass = mapperModel.map(origin, EmptyConstructorClass::class)
        assertEquals(origin.string, emptyConstructorClass.testString)
        assertEquals(origin.int, emptyConstructorClass.testInt)
    }

    @Test
    fun testMappingFullConstructorWithoutAnnotations() {
        val origin = OriginalClass(testString = "Test String", testInt = 4444)
        val destination = mapperModel.map(origin, DestinationRecordClass::class)

        assertEquals(origin.testString, destination.testString)
        assertEquals(origin.testInt, destination.testInt)
    }

    @Test
    fun testMappingFullConstructorWithAnnotations() {
        val origin = getMockAnnotated()
        val destination = mapperModel.map(origin, DestinationRecordClass::class)

        assertEquals(origin.string, destination.testString)
        assertEquals(origin.int, destination.testInt)
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
        @property:MapperModelField(destinationField = "testString", destinationClass = "EmptyConstructorClass")
        @property:MapperModelField(destinationField = "testString", destinationClass = "DestinationRecordClass")
        val string: String,
        @property:MapperModelField(destinationField = "testInt", destinationClass = "EmptyConstructorClass")
        @property:MapperModelField(destinationField = "testInt", destinationClass = "DestinationRecordClass")
        val int: Int
    )

    class EmptyConstructorClass {
        var testString: String? = null
        var testInt: Int? = null
    }

    data class DestinationRecordClass(val testString: String, val testInt: Int)

}