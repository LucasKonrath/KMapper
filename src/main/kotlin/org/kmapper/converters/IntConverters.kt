package org.kmapper.converters

import kotlin.reflect.KClassifier

class IntConverters {
    val converters: MutableMap<Pair<KClassifier?, KClassifier?>, ((Any) -> Any)> = mutableMapOf()

    init {

        converters[Pair(Int::class, Int::class)] = { from ->
            from
        }

        converters[Pair(Int::class, String::class)] = { from ->
            from as Int
            from.toString()
        }

        converters[Pair(Int::class, Double::class)] = { from ->
            from as Int
            from.toDouble()
        }

        converters[Pair(Int::class, Byte::class)] = { from ->
            from as Int
            from.toByte()
        }

        converters[Pair(Int::class, Short::class)] = { from ->
            from as Int
            from.toShort()
        }

        converters[Pair(Int::class, Char::class)] = { from ->
            from as Int
            from.toChar()
        }
    }

}