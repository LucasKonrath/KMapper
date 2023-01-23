package mapper

import kotlin.reflect.KClassifier

class Converters {

    private val converters: MutableMap<Pair<KClassifier?, KClassifier?>, ((Any) -> Any)> = mutableMapOf()

    /**
     * All KTypes:
     *  Number -> Byte, Short, Int, Long, Float, Double
     *  Boolean
     *  Char
     *  String
     *  Array -> Array<T>, ByteArray, ShortArray, IntArray
     */
    init {

        converters[Pair(String::class, String::class)] = { from ->
            from
        }

        converters[Pair(String::class, Int::class)] = { from ->
            from as String
            from.toInt()
        }

        converters[Pair(String::class, Double::class)] = { from ->
            from as String
            from.toDouble()
        }

        converters[Pair(Int::class, Int::class)] = { from ->
            from as Int
            from
        }

        converters[Pair(Int::class, Double::class)] = { from ->
            from as Int
            from.toDouble()
        }
    }

    /**
     * Returns the converter to be used for converting between two Kotlin classes on the MapperModel class.
     */
    fun getConverter(type1: KClassifier?, type2: KClassifier?) = converters[Pair(type1, type2)]

}