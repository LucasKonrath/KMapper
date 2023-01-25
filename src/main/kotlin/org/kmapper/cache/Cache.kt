package org.kmapper.cache


import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeUnit.MILLISECONDS

interface Cache {
    fun get(key: String): Any?
    fun put(key: String, value: Any)
}

class TimedCache() : Cache {
    /**
     * 15 minutes cache.
     */
    private var cacheTimeValidityInMillis: Long = 900000
    private val hashMap = ConcurrentHashMap<String, TimedEntry>()

    companion object {
        fun expiringEvery(duration: Long, timeUnit: TimeUnit) =
            TimedCache().apply {
                cacheTimeValidityInMillis = MILLISECONDS.convert(duration, timeUnit)
            }
    }

    override fun get(key: String): Any? {
        val timedEntry = hashMap[key]
        if (timedEntry == null || timedEntry.isExpired()) {
            println("cache miss for key $key")
            return null
        }

        return timedEntry.value
    }

    override fun put(key: String, value: Any) {
        println("caching $key with value $value")
        hashMap[key] = TimedEntry(value, cacheTimeValidityInMillis)
    }

    data class TimedEntry(val value: Any, val maxDurationInMillis: Long) {
        private val creationTime: Long = now()

        fun isExpired() = (now() - creationTime) > maxDurationInMillis

        private fun now() = System.currentTimeMillis()
    }
}