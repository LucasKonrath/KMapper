package org.kmapper.converters

import kotlin.reflect.KClassifier

class DoubleConverters {
    val converters: MutableMap<Pair<KClassifier?, KClassifier?>, ((Any) -> Any)> = mutableMapOf()

    init {

        converters[Pair(Double::class, Double::class)] = { from ->
            from
        }

        converters[Pair(Double::class, Int::class)] = { from ->
            from as Double
            from.toInt()
        }

        converters[Pair(Double::class, String::class)] = { from ->
            from as Double
            from.toString()
        }

        converters[Pair(Double::class, Byte::class)] = { from ->
            from as Double
            from.toInt().toByte()
        }

        converters[Pair(Double::class, Short::class)] = { from ->
            from as Double
            from.toInt().toShort()
        }

        converters[Pair(Double::class, Char::class)] = { from ->
            from as Double
            from.toInt().toChar()
        }
    }

}