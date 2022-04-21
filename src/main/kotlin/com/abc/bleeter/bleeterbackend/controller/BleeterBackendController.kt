package com.abc.bleeter.bleeterbackend.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/bleeter")
class BleeterBackendController {

    @GetMapping("/getBleets")
    @ResponseBody
    fun blog(): String {
        return "Hello World!";
    }

}