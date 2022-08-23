package com.abc.bleeter.bleeterbackend.process

import com.abc.bleeter.bleeterbackend.exceptions.NoBleetsDetectedException
import com.abc.bleeter.bleeterbackend.mapper.BleetResponseMapper
import com.abc.bleeter.bleeterbackend.model.*
import com.abc.bleeter.bleeterbackend.service.BleeterBackendService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.mapstruct.factory.Mappers
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*
import kotlin.collections.ArrayList


@Component
class BleetBackendProcess {

    @Autowired
    private lateinit var service: BleeterBackendService

    private var mapper: BleetResponseMapper = Mappers.getMapper(BleetResponseMapper::class.java)

    private var gson: Gson = GsonBuilder().setPrettyPrinting().create()

    private var pattern: String = "MMM dd, YYYY HH:MM"

    /* If needed in the future */
    // private var simpleDateFormat: SimpleDateFormat = SimpleDateFormat(pattern, Locale("en", "DE"))

    var BEFORE_ONE_DAY: LocalDateTime = LocalDateTime.now().minusDays(1)

    var BEFORE_ONE_HOUR: LocalDateTime = LocalDateTime.now().minusHours(1) /* ms */

    var BEFORE_ONE_MINUTE: LocalDateTime = LocalDateTime.now().minusMinutes(1) /* ms */

    fun findAllBleets() : Bleets {
        val bleets = Bleets()
        bleets.addAllBleetObjects(formatBleetTimestamps(service.findAllBleets()))
        return if (bleets.bleetObjects.isNotEmpty()) bleets else throw NoBleetsDetectedException("No Bleets Detected!!!", Bleet::class.java, "Bleets not found", "bleets")
    }

    fun findAllBleetsByBleeter(user: String): String {
        val bleetsByBleeter : List<Bleet> = service.findAllBleetsByBleeter(user)
        return if (bleetsByBleeter.isNotEmpty()) gson.toJson(bleetsByBleeter, List::class.java) else throw NoBleetsDetectedException(
            "No Bleets for the user $user Detected!!", Bleet::class.java, "bleetUser", user
        )
    }

    fun processBleet(bleetRequest: BleetRequest) : String {
        return gson.toJson(service.processBleet(mapper.bleetRequestToBleet(bleetRequest)))
    }

    fun deleteBleetsByBleeterAndBleetMessage(deleteBleetRequest: DeleteBleetRequest): String? {
        return gson.toJson(service.deleteBleetsByBleeterAndBleetMessage(deleteBleetRequest))
    }

    fun formatBleetTimestamps(bleets: List<Bleet>): MutableList<BleetObject> {

        val formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm:ss.S")

        val bleetList = bleets.sortedByDescending { it.bleetTimestamp }

        val bleetResponseObjectList: MutableList<BleetObject> = ArrayList()

        for (bleet in bleetList) {
            var bleetResponseObject = BleetObject()
            if (bleet.bleetTimestamp.toString().length > 20)
                bleet.bleetTimestamp = Timestamp.valueOf(bleet.bleetTimestamp.toString().substring(0, 19))
            val dateTime = LocalDateTime.parse(bleet.bleetTimestamp.toString(), formatter)

            if (BEFORE_ONE_DAY.isBefore(dateTime)) {
                if (BEFORE_ONE_HOUR.isBefore(dateTime)) {
                    if (BEFORE_ONE_MINUTE.isBefore(dateTime)) {
                        bleetResponseObject.bleetTimestamp = "moments Ago"

                    } else {
                        val minuteDifference = ChronoUnit.MINUTES.between(dateTime, LocalDateTime.now()).toString().replace("-", "")
                        bleetResponseObject.bleetTimestamp = if (minuteDifference.toInt() == 1) "$minuteDifference minute ago" else "$minuteDifference minutes ago"

                    }
                } else {
                    val hourDifference = ChronoUnit.HOURS.between(LocalDateTime.now(), dateTime).toString().replace("-", "")
                    bleetResponseObject.bleetTimestamp =  if (hourDifference.toInt() == 1) "$hourDifference hour ago" else "$hourDifference hours ago"

                }
            } else {
                bleetResponseObject.bleetTimestamp = dateTime.format(DateTimeFormatter.ofPattern(pattern)).toString().substring(0,12)

            }

            bleetResponseObject.bleetUser = bleet.bleetUser
            bleetResponseObject.bleetMessage = bleet.bleetMessage
            bleetResponseObject.bleetimage = bleet.bleetimage
            bleetResponseObject.id = bleet.id
            val bleetUser: BleetUser = service.getBleetUserFromUsername(bleet.bleetUser)
            bleetResponseObject.bleetFirstname = bleetUser.bleetFirstname
            bleetResponseObject.verified = bleetUser.verified
            bleetResponseObject.bleetLastname = bleetUser.bleetLastname
            bleetResponseObject.profilepic = bleetUser.profilepic

            bleetResponseObjectList.add(bleetResponseObject)
        }
        return bleetResponseObjectList
    }
}