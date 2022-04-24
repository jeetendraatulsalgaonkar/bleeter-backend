package com.abc.bleeter.bleeterbackend.controller

import com.abc.bleeter.bleeterbackend.model.BleetRequest
import com.abc.bleeter.bleeterbackend.process.BleetBackendProcess
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/bleeter")
class BleeterBackendController {

    @Autowired
    private lateinit var process: BleetBackendProcess

    @RequestMapping(
        value = ["/getBleets"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getBleets(): ResponseEntity<String> {
        return ResponseEntity.ok().body(process.findAllBleets())
    }

    @RequestMapping(
        value = ["/getBleetsByBleeter"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun getBleetsByBleeter(@RequestParam bleetUser: String): ResponseEntity<String> {
        return ResponseEntity.ok().body(process.findAllBleetsByBleeter(bleetUser))
    }

    @RequestMapping(
        value = ["/bleet"],
        method = [RequestMethod.POST],
        produces = [MediaType.APPLICATION_JSON_VALUE],
        consumes = [MediaType.APPLICATION_JSON_VALUE]
    )
    @ResponseBody
    fun bleet(@RequestBody bleetRequest: BleetRequest): ResponseEntity<String> {
        return ResponseEntity.status(201).body(ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(process.processBleet(bleetRequest)))
    }

}