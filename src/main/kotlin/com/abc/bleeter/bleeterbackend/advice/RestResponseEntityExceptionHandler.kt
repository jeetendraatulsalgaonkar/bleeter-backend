package com.abc.bleeter.bleeterbackend.advice

import com.abc.bleeter.bleeterbackend.exceptions.NoBleetsDetectedException
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    private var gson: Gson = GsonBuilder().setPrettyPrinting().create()

    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    fun handleIllegalArgumentException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = gson.toJson(ex.message)

        return handleExceptionInternal(
            ex, bodyOfResponse,
            getResponseHeaders(), HttpStatus.BAD_REQUEST, request
        )
    }

    @ExceptionHandler(value = [NoBleetsDetectedException::class])
    fun handleNoBleetException(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val bodyOfResponse = gson.toJson(ex.message)
        return handleExceptionInternal(
            ex, bodyOfResponse,
            getResponseHeaders(), HttpStatus.NO_CONTENT, request
        )
    }

    fun getResponseHeaders() : HttpHeaders {
        var httpHeaders = HttpHeaders()
        httpHeaders.setContentType(MediaType.APPLICATION_JSON)
        return httpHeaders
    }
}