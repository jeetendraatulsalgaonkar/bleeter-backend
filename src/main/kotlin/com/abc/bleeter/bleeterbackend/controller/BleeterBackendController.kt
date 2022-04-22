package com.abc.bleeter.bleeterbackend.controller

import com.abc.bleeter.bleeterbackend.service.BleeterBackendService
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody


@Controller
@RequestMapping("/bleeter")
class BleeterBackendController {

    @Autowired
    private lateinit var service: BleeterBackendService;

    @GetMapping("/getBleets")
    @ResponseBody
    fun getBleets(): String {
        return ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(service.findAllBleets());
    }

    @GetMapping("/getBleetsByBleeter")
    @ResponseBody
    fun getBleetsByBleeter(@RequestParam bleetUser: String): String {
        return ObjectMapper().writer().withDefaultPrettyPrinter().writeValueAsString(service.findAllBleetsByBleeter(bleetUser));
    }

}