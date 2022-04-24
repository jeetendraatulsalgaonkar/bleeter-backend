package com.abc.bleeter.bleeterbackend.process

import com.abc.bleeter.bleeterbackend.mapper.BleetResponseMapper
import com.abc.bleeter.bleeterbackend.model.BleetRequest
import com.abc.bleeter.bleeterbackend.service.BleeterBackendService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class BleetBackendProcess {

    @Autowired
    private lateinit var service: BleeterBackendService

    private var mapper: BleetResponseMapper = Mappers.getMapper(BleetResponseMapper::class.java)

    private var gson: Gson = GsonBuilder().setPrettyPrinting().create()

    fun findAllBleets() : String {
        return gson.toJson(service.findAllBleets(), List::class.java)
    }

    fun findAllBleetsByBleeter(user: String): String {
        return gson.toJson(service.findAllBleets(), List::class.java)
    }

    fun processBleet(bleetRequest: BleetRequest) : String {
        return gson.toJson(service.processBleet(mapper.bleetRequestToBleet(bleetRequest)))
    }
}