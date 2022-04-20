package com.abc.bleeter.bleeterbackend.controller

import com.abc.bleeter.bleeterbackend.model.Bleet
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class BleeterBackendController {

    @GetMapping("/")
    fun blog(bleet: Bleet): String {
        return bleet.bleetMessage;
    }

}