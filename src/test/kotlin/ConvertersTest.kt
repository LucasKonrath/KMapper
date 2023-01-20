import mapper.Converters
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class ConvertersTest {

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
    fun convertIntToInt() {
        val from = 10
        val to: Any = converters.getConverter(from::class, Int::class)?.invoke(from)!!
        Assertions.assertEquals(from, to)
    }

    @Test
    fun convertIntToDouble() {
        val from = 10
        val to: Any = converters.getConverter(from::class, Double::class)?.invoke(from)!!
        Assertions.assertEquals(from.toDouble(), to)
    }
}