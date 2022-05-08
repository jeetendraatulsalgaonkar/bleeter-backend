package com.abc.bleeter.bleeterbackend.advice

import com.abc.bleeter.bleeterbackend.exceptions.NoBleetsDetectedException
import com.abc.bleeter.bleeterbackend.model.error.ApiError
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatus.NOT_FOUND
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import org.springframework.web.util.WebUtils


@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    private var gson: Gson = GsonBuilder().setPrettyPrinting().create()

    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    fun handleIllegalArgumentException(ex: RuntimeException, request: WebRequest): ResponseEntity<String> {
        val bodyOfResponse = gson.toJson(ex.message)
        val apiError = ApiError(HttpStatus.BAD_REQUEST, ex, ex.message!!)


        return handleExceptionInternal(
            ex, bodyOfResponse,
            getResponseHeaders(), HttpStatus.BAD_REQUEST, request
        )
    }

    @ExceptionHandler(value = [NoBleetsDetectedException::class])
    fun handleNoBleetException(ex: NoBleetsDetectedException, request: WebRequest): ResponseEntity<ApiError> {
        val apiError = ApiError(NOT_FOUND)
        apiError.setMessage(ex.getMessage())
        return buildResponseEntity(apiError)
    }

    fun getResponseHeaders() : HttpHeaders {
        var httpHeaders = HttpHeaders()
        httpHeaders.setContentType(MediaType.APPLICATION_JSON)
        return httpHeaders
    }

    fun handleExceptionInternal(ex: Exception, body: String, headers: HttpHeaders, status: HttpStatus, request: WebRequest): ResponseEntity<String> {
        if (HttpStatus.INTERNAL_SERVER_ERROR == status) {
            request.setAttribute(WebUtils.ERROR_EXCEPTION_ATTRIBUTE, ex, WebRequest.SCOPE_REQUEST)
        }
        return ResponseEntity(body, headers, status)
    }
    private fun buildResponseEntity(apiError: ApiError): ResponseEntity<ApiError> {
        return ResponseEntity(apiError, apiError.getStatus())
    }

}