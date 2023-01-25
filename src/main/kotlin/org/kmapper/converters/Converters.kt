package org.kmapper.converters

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
        converters.putAll(StringConverters().converters)
        converters.putAll(IntConverters().converters)
        converters.putAll(DoubleConverters().converters)
        converters.putAll(ByteConverters().converters)
        converters.putAll(ShortConverters().converters)
    }

    /**
     * Returns the converter to be used for converting between two Kotlin classes on the MapperModel class.
     */
    fun getConverter(type1: KClassifier?, type2: KClassifier?) = converters[Pair(type1, type2)]

    fun addConverter(originType: KClassifier?, destinationType: KClassifier?, mappingFunction: ((Any) -> Any)) {
        converters[Pair(originType, destinationType)] = mappingFunction
    }
}