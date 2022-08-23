package com.abc.bleeter.bleeterbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer


@SpringBootApplication(exclude = [ErrorMvcAutoConfiguration::class], scanBasePackages = ["com.abc.bleeter.bleeterbackend"])
class BleeterBackendApplication

fun main(args: Array<String>) {
	runApplication<BleeterBackendApplication>(*args)
}

@Bean
fun corsConfigurer(): WebMvcConfigurer {
	return object : WebMvcConfigurer {
		override fun addCorsMappings(registry: CorsRegistry) {
			registry
				.addMapping("*")
				.allowedOrigins("*").maxAge(3600);

			/*
				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "X-Requested-With", "Content-Type", "Accept")
				.allowedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Accept", "Content-Type")
				.exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Headers", "Accept", "Content-Type")
				.allowCredentials(false)
			 */
		}
	}
}
