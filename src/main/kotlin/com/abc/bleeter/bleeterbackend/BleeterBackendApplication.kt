package com.abc.bleeter.bleeterbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class BleeterBackendApplication

fun main(args: Array<String>) {
	runApplication<BleeterBackendApplication>(*args)
}
