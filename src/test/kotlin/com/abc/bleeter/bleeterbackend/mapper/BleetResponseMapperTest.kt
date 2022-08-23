package com.abc.bleeter.bleeterbackend.mapper

import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.model.BleetResponse
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mapstruct.factory.Mappers
import java.sql.Timestamp
import java.util.*
import java.util.stream.Stream


class BleetResponseMapperTest {

    @ParameterizedTest
    @MethodSource("getBleet")
    fun testBleetToBleetResponse(bleet: Bleet) {
        val mapper = Mappers.getMapper(BleetResponseMapper::class.java)
        val bleetResponse = mapper.bleetToBleetResponse(bleet)
        assertThat(bleetResponse).isNotNull
        // assertEquals(bleetResponse.id, bleet.id)
        // assertEquals(bleetResponse.bleetMessage, bleet.bleetMessage)
    }

    companion object {
        @JvmStatic
        fun getBleetResponse(): Stream<Arguments> {
            return Stream.of(Arguments.of(BleetResponse(UUID.randomUUID().toString(), "Twitter without T", "ElonMusk", "2022-04-21 19:49:51")))
        }

        @JvmStatic
        fun getBleet(): Stream<Arguments> {
            var bleet: Bleet = Bleet()
            bleet.bleetTimestamp = Timestamp(1660997172L)
            bleet.id = UUID.randomUUID().toString()
            bleet.bleetMessage = "Twitter without T"
            bleet.bleetUser = "ElonMusk"
            return Stream.of(
                Arguments.of(
                    bleet
                )
            )
        }
    }
}