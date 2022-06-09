package com.abc.bleeter.bleeterbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration
import org.springframework.boot.runApplication

@SpringBootApplication(exclude = [DataSourceAutoConfiguration::class])
class BleeterBackendApplication{
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            runApplication<BleeterBackendApplication>(*args)
        }
    }
}