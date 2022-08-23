package com.abc.bleeter.bleeterbackend.mapper

import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.model.BleetRequest
import com.abc.bleeter.bleeterbackend.model.BleetResponse
import org.mapstruct.InheritInverseConfiguration
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

@Mapper
interface BleetResponseMapper {

    @Mapping(target = "id", source="id")
    @Mapping(target = "bleetMessage", source="bleetMessage")
    @Mapping(target = "bleetUser", source="bleetUser")
    @Mapping(target = "bleetTimestamp", expression ="java(bleet.bleetTimestamp.toString())")
    fun bleetToBleetResponse(bleet: Bleet) : BleetResponse

    @Mapping(target = "id", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "bleetMessage", source="bleetMessage")
    @Mapping(target = "bleetUser", source="bleetUser")
    @Mapping(target = "bleetTimestamp", expression = "java(new Timestamp(System.currentTimeMillis()))")
    fun bleetRequestToBleet(bleetRequest: BleetRequest) : Bleet

    @InheritInverseConfiguration
    @Mapping(target = "bleetMessage", source="bleetMessage")
    @Mapping(target = "bleetUser", source="bleetUser")
    fun bleetToBleetRequest(bleet: Bleet) : BleetRequest

    fun map(timestamp : Timestamp) : String {
        return SimpleDateFormat("dd/MM/yyyy HH:mm:ss.ssssss").format(Date(timestamp.toString().toLong())).toString()
    }

}