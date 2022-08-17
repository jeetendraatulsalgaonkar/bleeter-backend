package com.abc.bleeter.bleeterbackend.process

import com.abc.bleeter.bleeterbackend.exceptions.NoBleetsDetectedException
import com.abc.bleeter.bleeterbackend.mapper.BleetResponseMapper
import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.model.BleetRequest
import com.abc.bleeter.bleeterbackend.model.Bleets
import com.abc.bleeter.bleeterbackend.model.DeleteBleetRequest
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

    fun findAllBleets() : Bleets {
        val bleets = Bleets()
        bleets.addAllBleets(service.findAllBleets())
        return if (bleets.bleets.isNotEmpty()) bleets else throw NoBleetsDetectedException("No Bleets Detected!!!", Bleet::class.java, "Bleets not found", "bleets")
    }

    fun findAllBleetsByBleeter(user: String): String {
        val bleetsByBleeter : List<Bleet> = service.findAllBleetsByBleeter(user)
        return if (bleetsByBleeter.isNotEmpty()) gson.toJson(bleetsByBleeter, List::class.java) else throw NoBleetsDetectedException(
            "No Bleets for the user $user Detected!!", Bleet::class.java, "bleetUser", user
        )
    }

    fun processBleet(bleetRequest: BleetRequest) : String {
        return gson.toJson(service.processBleet(mapper.bleetRequestToBleet(bleetRequest)))
    }

    fun deleteBleetsByBleeterAndBleetMessage(deleteBleetRequest: DeleteBleetRequest): String? {
        return gson.toJson(service.deleteBleetsByBleeterAndBleetMessage(deleteBleetRequest))
    }
}