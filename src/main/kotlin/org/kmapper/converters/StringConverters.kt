package org.kmapper.converters

import kotlin.reflect.KClassifier

class StringConverters {
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

        converters[Pair(String::class, Byte::class)] = { from ->
            from as String
            from.toByte()
        }

        converters[Pair(String::class, Short::class)] = { from ->
            from as String
            from.toShort()
        }
    }

}