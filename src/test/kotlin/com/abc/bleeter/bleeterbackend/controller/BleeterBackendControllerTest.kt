package com.abc.bleeter.bleeterbackend.controller

import com.abc.bleeter.bleeterbackend.exceptions.NoBleetsDetectedException
import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.model.Bleets
import com.abc.bleeter.bleeterbackend.process.BleetBackendProcess
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.*
import java.util.stream.Stream
import kotlin.collections.ArrayList

@ExtendWith(SpringExtension::class)
@WebMvcTest(BleeterBackendController::class)
class BleeterBackendControllerTest (@Autowired val mockMvc: MockMvc) {

    @MockkBean
    lateinit var process: BleetBackendProcess

    private var gson: Gson = GsonBuilder().setPrettyPrinting().create()

    @ParameterizedTest
    @MethodSource("getBleets")
    fun getBleets(bleets: Bleets) {
        every {process.findAllBleets()} returns bleets
        mockMvc.perform(get("/bleeter/getBleets"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    @ParameterizedTest
    @MethodSource("getEmptyBleetList")
    fun getBleets_NoBleetsReturned(bleets: List<Bleet>) {
        every {process.findAllBleets()} throws NoBleetsDetectedException("No Bleets detected!!!", this.javaClass, "findAllBleets", "bleets")
        mockMvc.perform(get("/bleeter/getBleets"))
            .andExpect(status().isNotFound)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    @ParameterizedTest
    @MethodSource("getBleetUser")
    fun getBleetsByBleeter(user: String) {
        var bleets: MutableList<Bleet> = ArrayList()
        val bleet1: Bleet = Bleet(UUID.randomUUID().toString(), "Twitter without T", "ElonMusk", "2022-04-21 19:49:51")
        bleets.add(bleet1)
        every {process.findAllBleetsByBleeter(user)} returns gson.toJson(bleets)
        mockMvc.perform(get("/bleeter/getBleetsByBleeter?bleetUser=$user"))
            .andExpect(status().isOk)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    @ParameterizedTest
    @MethodSource("getBleetUser")
    fun getBleetsByBleeter_NoBleetsReturned(user: String) {
        every {process.findAllBleetsByBleeter(user)} throws NoBleetsDetectedException("No Bleets for the user $user Detected!!", this.javaClass, "getBleetsByBleeter", "bleets")
        mockMvc.perform(get("/bleeter/getBleetsByBleeter?bleetUser=$user"))
            .andExpect(status().isNotFound)
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
    }

    companion object {

        @JvmStatic
        fun getBleets() : Stream<Arguments> {
            val bleets = Bleets()
            var bleetList: MutableList<Bleet> = ArrayList()
            var bleet1: Bleet = Bleet(UUID.randomUUID().toString(), "Twitter without T", "ElonMusk", "2022-04-21 19:49:51")
            bleetList.add(bleet1)
            bleets.addAllBleets(bleetList)
            return Stream.of(
                Arguments.of(bleets)
            )
        }

        @JvmStatic
        fun getEmptyBleetList() : Stream<Arguments> {
            var bleets: MutableList<Bleet> = ArrayList()
            return Stream.of(
                Arguments.of(bleets)
            )
        }

        @JvmStatic
        fun getBleetUser() : Stream<Arguments> {
            return Stream.of(
                Arguments.of("ElonMusk")
            )
        }
    }

}