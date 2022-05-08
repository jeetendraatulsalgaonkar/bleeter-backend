package com.abc.bleeter.bleeterbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class BleeterBackendApplication

fun main(args: Array<String>) {
	runApplication<BleeterBackendApplication>(*args)
}
