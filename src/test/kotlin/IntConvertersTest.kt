import mapper.converters.Converters
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class IntConvertersTest {

    private val converters = Converters()

    @Test
    fun convertIntToString() {
        val from = 10
        val to: Any = converters.getConverter(from::class, String::class)?.invoke(from)!!
        Assertions.assertEquals(from.toString(), to)
    }

    @Test
    fun convertIntToInt() {
        val from = 10
        val to: Any = converters.getConverter(from::class, Int::class)?.invoke(from)!!
        Assertions.assertEquals(from.toInt(), to)
    }

    @Test
    fun convertIntToDouble() {
        val from = 10
        val to: Any = converters.getConverter(from::class, Double::class)?.invoke(from)!!
        Assertions.assertEquals(from.toDouble(), to)
    }

    @Test
    fun convertIntToByte() {
        val from = 10
        val to: Any = converters.getConverter(from::class, Byte::class)?.invoke(from)!!
        Assertions.assertEquals(from.toByte(), to)
    }

    @Test
    fun convertIntToShort() {
        val from = 10
        val to: Any = converters.getConverter(from::class, Short::class)?.invoke(from)!!
        Assertions.assertEquals(from.toShort(), to)
    }

}