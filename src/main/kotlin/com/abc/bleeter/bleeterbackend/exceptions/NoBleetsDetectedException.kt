package com.abc.bleeter.bleeterbackend.exceptions

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class NoBleetsDetectedException(override val message: String) : RuntimeException(message) {
    @JvmName("getMessage1")
    fun getMessage(): String {
        return this.message
    }

}