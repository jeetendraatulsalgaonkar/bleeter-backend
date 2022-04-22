package com.abc.bleeter.bleeterbackend.mapper

import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.model.BleetResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.mapstruct.factory.Mappers
import java.sql.Timestamp


class BleetResponseMapperTest {

    @Test
    fun testBleetToBleetResponse() {
        val mapper = Mappers.getMapper(BleetResponseMapper::class.java);

        val bleet = Bleet("24586846513513", "Twitter without T", "ElonMusk", "2022-04-21 19:49:51")
        val bleetResponse = mapper.bleetToBleetResponse(bleet)
        assertThat(bleetResponse).isNotNull
        assertEquals(bleetResponse.bleetId, bleet.bleetId)
        assertEquals(bleetResponse.bleetMessage, bleet.bleetMessage)
        assertEquals(bleetResponse.bleetTimestamp, bleet.bleetTimestamp.toString())
    }

    @Test
    fun testBleetResponseToBleet() {
        val mapper = Mappers.getMapper(BleetResponseMapper::class.java);

        val bleetResponse = BleetResponse("24586846513513", "Twitter without T", "ElonMusk", "2022-04-21 19:49:51")
        val bleet = mapper.bleetResponseToBleet(bleetResponse)
        assertThat(bleetResponse).isNotNull
        assertEquals(bleetResponse.bleetId, bleet.bleetId)
        assertEquals(bleetResponse.bleetMessage, bleet.bleetMessage)
        assertEquals(bleetResponse.bleetTimestamp, bleet.bleetTimestamp.toString())
    }

}