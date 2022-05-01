package com.abc.bleeter.bleeterbackend.exceptions

class NoBleetsDetectedException(override val message: String?) : RuntimeException(message)