import kotlin.reflect.KClass
import kotlin.reflect.KClassifier

class Converters {

    val converters: MutableMap<Pair<KClassifier?, KClassifier?>, ((Any) -> Any)> = mutableMapOf()

    init {

        converters[Pair(String::class, Int::class)] = { to ->
            to as String
            to.toInt()
        }

        converters[Pair(String::class, String::class)] = { to ->
            to
        }

        converters[Pair(String::class, Double::class)] = { to ->
            to as String
            to.toDouble()
        }

        converters[Pair(Int::class, Int::class)] = { to ->
            to as Int
            to
        }

        converters[Pair(Int::class, Double::class)] = { to ->
            to as Int
            to.toDouble()
        }
    }

    fun getConverter(type1:KClassifier?, type2:KClassifier?) = converters[Pair(type1, type2)]

}