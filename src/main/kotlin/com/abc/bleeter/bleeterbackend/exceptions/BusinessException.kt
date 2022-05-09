package com.abc.bleeter.bleeterbackend.exceptions

open class BusinessException (override var message: String) : RuntimeException(message) {

}