package com.abc.bleeter.bleeterbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class, ErrorMvcAutoConfiguration::class])
class BleeterBackendApplication

fun main(args: Array<String>) {
	runApplication<BleeterBackendApplication>(*args)
}
