package com.abc.bleeter.bleeterbackend.model.error

import com.abc.bleeter.bleeterbackend.model.LowerCaseClassNameResolver
import com.fasterxml.jackson.annotation.JsonFormat
import com.fasterxml.jackson.annotation.JsonTypeInfo
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver
import lombok.Data
import org.hibernate.validator.internal.engine.path.PathImpl
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import java.time.LocalDateTime
import java.util.function.Consumer
import javax.validation.ConstraintViolation


@Data
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.CUSTOM, property = "error", visible = true)
@JsonTypeIdResolver(LowerCaseClassNameResolver::class)
@Order(Ordered.HIGHEST_PRECEDENCE)
class ApiError {

    private var status: HttpStatus? = null

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private var timestamp: LocalDateTime = LocalDateTime.now()
    private lateinit var message: String
    private lateinit var debugMessage: String
    private lateinit var subErrors: MutableList<ApiSubError>

    constructor(status: HttpStatus) {
        this
        this.status = status
    }

    constructor(status: HttpStatus, ex: Throwable) {
        this
        this.status = status
        this.message = "Unexpected Error Thrown."
        this.debugMessage = ex.localizedMessage
    }

    constructor(status: HttpStatus, ex: Throwable, message: String) {
        this
        this.status = status
        this.message = message
        this.debugMessage = ex.localizedMessage
    }

    fun addSubError(subError: ApiSubError) {
        if (subErrors == null) {
            subErrors = ArrayList()
        }
        subErrors.add(subError)
    }
    private fun addValidationError(`object`: String, field: String, rejectedValue: Any, message: String) {
        addSubError(ApiValidationError(`object`, field, rejectedValue, message))
    }

    private fun addValidationError(`object`: String, message: String) {
        addSubError(ApiValidationError(`object`, message))
    }

    private fun addValidationError(fieldError: FieldError) {
        fieldError.rejectedValue?.let { rejectedValue ->
            fieldError.defaultMessage?.let { defaultMessage ->
                this.addValidationError(
                    fieldError.objectName,
                    fieldError.field,
                    rejectedValue,
                    defaultMessage
                )
            }
        }
    }

    fun addValidationErrors(fieldErrors: List<FieldError>) {
        fieldErrors.forEach(Consumer { fieldError: FieldError ->
            this.addValidationError(
                fieldError
            )
        })
    }

    private fun addValidationError(objectError: ObjectError) {
        objectError.defaultMessage?.let { defaultMessage ->
            this.addValidationError(
                objectError.objectName,
                defaultMessage
            )
        }
    }

    fun addValidationError(globalErrors: List<ObjectError>) {
        globalErrors.forEach(Consumer { objectError: ObjectError ->
            this.addValidationError(
                objectError
            )
        })
    }

    /**
     * Utility method for adding error of ConstraintViolation. Usually when a @Validated validation fails.
     *
     * @param cv the ConstraintViolation
     */
    private fun addValidationError(cv: ConstraintViolation<*>) {
        this.addValidationError(
            cv.rootBeanClass.simpleName,
            (cv.propertyPath as PathImpl).getLeafNode().asString(),
            cv.invalidValue,
            cv.message
        )
    }

    fun addValidationErrors(constraintViolations: Set<ConstraintViolation<*>>) {
        constraintViolations.forEach(Consumer { cv: ConstraintViolation<*> ->
            this.addValidationError(
                cv
            )
        })
    }

    fun setMessage(message: String) {
        this.message = message
    }

    fun getStatus(): HttpStatus {
        return this.status!!
    }
}