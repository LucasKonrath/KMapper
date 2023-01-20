import mapper.MapperModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class MapperModelTest {

    val mapperModel = MapperModel()

    @Test
    fun testMappingEmptyConstructorWithoutAnnotations() {
        val origin = OriginalClass(testString = "Test String", testInt = 4444)
        val emptyConstructorClass = mapperModel.map(origin, EmptyConstructorClass::class)

        assertEquals(origin.testString, emptyConstructorClass.testString)
        assertEquals(origin.testInt, emptyConstructorClass.testInt)
    }

    data class OriginalClass(
        val testString: String,
        val testInt: Int
    )

    class EmptyConstructorClass {
        var testString: String? = null
        var testInt: Int? = null
    }

}