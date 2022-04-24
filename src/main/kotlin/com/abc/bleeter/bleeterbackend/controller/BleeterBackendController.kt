package com.abc.bleeter.bleeterbackend.controller

import com.abc.bleeter.bleeterbackend.mapper.BleetResponseMapper
import com.abc.bleeter.bleeterbackend.model.BleetRequest
import com.abc.bleeter.bleeterbackend.process.BleetBackendProcess
import com.fasterxml.jackson.databind.ObjectMapper
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/bleeter")
class BleeterBackendController {

    @Autowired
    private lateinit var process: BleetBackendProcess

    val mapper: BleetResponseMapper = Mappers.getMapper(BleetResponseMapper::class.java);

    @GetMapping("/getBleets")
    @ResponseBody
    fun getBleets(): ResponseEntity<String> {
        return ResponseEntity.ok().body(process.findAllBleets());
    }

    @GetMapping("/getBleetsByBleeter")
    @ResponseBody
    fun getBleetsByBleeter(@RequestParam bleetUser: String): ResponseEntity<String> {
        return ResponseEntity.ok().body(process.findAllBleetsByBleeter(bleetUser));
    }

    @PostMapping("/bleet")
    @ResponseBody
    fun bleet(@RequestBody bleetRequest: BleetRequest): ResponseEntity<String> {
        return ResponseEntity.status(201).body(ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(process.processBleet(bleetRequest)))
    }

}