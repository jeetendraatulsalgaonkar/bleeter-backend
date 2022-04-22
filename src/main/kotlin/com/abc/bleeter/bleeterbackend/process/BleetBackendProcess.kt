package com.abc.bleeter.bleeterbackend.process

import com.abc.bleeter.bleeterbackend.model.BleetRequest
import com.abc.bleeter.bleeterbackend.service.BleeterBackendService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BleetBackendProcess {

    @Autowired
    private lateinit var service: BleeterBackendService

    fun processBleet(bleetRequest: BleetRequest) : String {

        return "";
    }
}