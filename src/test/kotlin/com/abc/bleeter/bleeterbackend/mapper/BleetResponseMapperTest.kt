package com.abc.bleeter.bleeterbackend.mapper

import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.model.BleetResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers


class BleetResponseMapperTest {

    @Test
    fun testBleetToBleetResponse() {
        val mapper = Mappers.getMapper(BleetResponseMapper::class.java)

        val bleet = Bleet("62619949b4029ddfc3bf3e98", "Twitter without T", "ElonMusk", "2022-04-21 19:49:51")
        val bleetResponse = mapper.bleetToBleetResponse(bleet)
        assertThat(bleetResponse).isNotNull
        assertEquals(bleetResponse.id, bleet.id)
        assertEquals(bleetResponse.bleetMessage, bleet.bleetMessage)
        assertEquals(bleetResponse.bleetTimestamp, bleet.bleetTimestamp)
    }

    @Test
    fun testBleetResponseToBleet() {
        val mapper = Mappers.getMapper(BleetResponseMapper::class.java)

        val bleetResponse = BleetResponse("62619949b4029ddfc3bf3e98", "Twitter without T", "ElonMusk", "2022-04-21 19:49:51")
        val bleet = mapper.bleetResponseToBleet(bleetResponse)
        assertThat(bleetResponse).isNotNull
        assertEquals(bleetResponse.id, bleet.id)
        assertEquals(bleetResponse.bleetMessage, bleet.bleetMessage)
        assertEquals(bleetResponse.bleetTimestamp, bleet.bleetTimestamp)
    }

}