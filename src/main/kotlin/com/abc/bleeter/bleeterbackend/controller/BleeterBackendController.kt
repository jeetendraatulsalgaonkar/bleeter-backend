package com.abc.bleeter.bleeterbackend.controller

import com.abc.bleeter.bleeterbackend.mapper.BleetResponseMapper
import com.abc.bleeter.bleeterbackend.model.BleetRequest
import com.abc.bleeter.bleeterbackend.service.BleeterBackendService
import com.fasterxml.jackson.databind.ObjectMapper
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.net.URI
import java.util.stream.Collectors


@Controller
@RequestMapping("/bleeter")
class BleeterBackendController {

    @Autowired
    private lateinit var service: BleeterBackendService;

    val mapper: BleetResponseMapper = Mappers.getMapper(BleetResponseMapper::class.java);

    @GetMapping("/getBleets")
    @ResponseBody
    fun getBleets(): String {
        return ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(service.findAllBleets().stream().map { s -> {mapper.bleetToBleetResponse(s)} }.collect(Collectors.toList()));
    }

    @GetMapping("/getBleetsByBleeter")
    @ResponseBody
    fun getBleetsByBleeter(@RequestParam bleetUser: String): String {
        return ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(service.findAllBleetsByBleeter(bleetUser).stream().map { s -> {mapper.bleetToBleetResponse(s)} }.collect(Collectors.toList()));
    }

    @PostMapping("/bleet")
    @ResponseBody
    fun bleet(@RequestBody bleetRequest: BleetRequest): ResponseEntity<String> {
        return ResponseEntity.created(URI.create("/bleet")).build();
    }

}