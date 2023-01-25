package org.kmapper.converters

import kotlin.reflect.KClassifier

class ByteConverters {
    val converters: MutableMap<Pair<KClassifier?, KClassifier?>, ((Any) -> Any)> = mutableMapOf()

    init {

        converters[Pair(Byte::class, Byte::class)] = { from ->
            from
        }

        converters[Pair(Byte::class, Double::class)] = { from ->
            from as Byte
            from.toDouble()
        }

        converters[Pair(Byte::class, Int::class)] = { from ->
            from as Byte
            from.toInt()
        }

        converters[Pair(Byte::class, String::class)] = { from ->
            from as Byte
            from.toString()
        }

        converters[Pair(Byte::class, Short::class)] = { from ->
            from as Byte
            from.toInt().toShort()
        }

        converters[Pair(Byte::class, Char::class)] = { from ->
            from as Byte
            from.toInt().toChar()
        }
    }

}