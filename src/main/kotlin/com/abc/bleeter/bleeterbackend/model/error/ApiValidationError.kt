package com.abc.bleeter.bleeterbackend.model.error

import lombok.AllArgsConstructor
import lombok.Data
import lombok.EqualsAndHashCode

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
class ApiValidationError constructor() : ApiSubError() {
    private lateinit var `object`: String
    private lateinit var field: String
    private lateinit var rejectedValue: Any
    private lateinit var message: String

    constructor (`object`: String, message: String) : this() {
        this.`object` = `object`
        this.message = message
    }

    constructor(`object`: String, field: String, rejectedValue: Any, message: String): this() {
        this.`object` = `object`
        this.field = field
        this.rejectedValue = rejectedValue
        this.message = message
    }

}