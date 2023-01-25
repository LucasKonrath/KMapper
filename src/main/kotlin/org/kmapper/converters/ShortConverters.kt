package org.kmapper.converters

import kotlin.reflect.KClassifier

class ShortConverters {
    val converters: MutableMap<Pair<KClassifier?, KClassifier?>, ((Any) -> Any)> = mutableMapOf()

    init {

        converters[Pair(Short::class, Short::class)] = { from ->
            from
        }

        converters[Pair(Short::class, Byte::class)] = { from ->
            from as Short
            from.toByte()
        }

        converters[Pair(Short::class, Double::class)] = { from ->
            from as Short
            from.toDouble()
        }

        converters[Pair(Short::class, Int::class)] = { from ->
            from as Short
            from.toInt()
        }

        converters[Pair(Short::class, String::class)] = { from ->
            from as Short
            from.toString()
        }

        converters[Pair(Short::class, Char::class)] = { from ->
            from as Short
            from.toInt().toChar()
        }
    }

}