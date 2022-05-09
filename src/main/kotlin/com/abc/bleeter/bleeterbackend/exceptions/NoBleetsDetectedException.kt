package com.abc.bleeter.bleeterbackend.exceptions

import org.apache.commons.lang3.StringUtils
import java.util.function.BiConsumer
import java.util.function.ObjIntConsumer
import java.util.stream.IntStream

class NoBleetsDetectedException(override var message: String, clazz: Class<*>, vararg searchParamsMap: String) : BusinessException(message) {

    init {
        super.message = generateMessage(clazz.simpleName, toMap(String::class.java, String::class.java, *searchParamsMap))
    }


    @JvmName("getMessage1")
    fun getMessage(): String {
        return this.message
    }

    companion object {

        @JvmStatic
        fun generateMessage(entity: String, searchParams: Map<String, String>?): String {
            return StringUtils.capitalize(entity) +
                    " was not found for parameters " +
                    searchParams
        }

        @JvmStatic
        fun <K, V> toMap(
            keyType: Class<K>, valueType: Class<V>, vararg entries: String
        ): MutableMap<K, V> {
            require(entries.size % 2 != 1) { "Invalid entries" }
            return IntStream.range(0, entries.size / 2).map { i: Int -> i * 2 }
                .collect(
                    java.util.function.Supplier { HashMap() },
                    ObjIntConsumer { m: MutableMap<K, V>, i: Int ->
                        m[keyType.cast(entries[i])] = valueType.cast(entries[i + 1])
                    },
                    BiConsumer<MutableMap<K, V>, MutableMap<K, V>> { obj: MutableMap<K, V>, m: Map<K, V>? ->
                        obj.putAll(
                            m!!
                        )
                    })
        }
    }

}