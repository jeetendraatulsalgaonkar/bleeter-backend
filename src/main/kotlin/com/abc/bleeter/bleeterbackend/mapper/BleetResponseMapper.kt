package com.abc.bleeter.bleeterbackend.mapper

import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.model.BleetResponse
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@Mapper
interface BleetResponseMapper {

    fun bleetToBleetResponse(bleet: Bleet) : BleetResponse

    @InheritInverseConfiguration
    fun bleetResponseToBleet(bleetResponse: BleetResponse) : Bleet

    fun map(timestamp : Timestamp) : String {
        return SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(Date(timestamp.toString().toLong())).toString()
    }

}