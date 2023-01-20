package mapper

import kotlin.reflect.KClassifier

class Converters {

    val converters: MutableMap<Pair<KClassifier?, KClassifier?>, ((Any) -> Any)> = mutableMapOf()

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

    fun getConverter(type1: KClassifier?, type2: KClassifier?) = converters[Pair(type1, type2)]

}