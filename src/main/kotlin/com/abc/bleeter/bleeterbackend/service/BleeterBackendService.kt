package com.abc.bleeter.bleeterbackend.service

import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.repository.BleetsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class BleeterBackendService {

    @Autowired
    private lateinit var bleetsRepository: BleetsRepository;

    fun findAllBleets() : List<Bleet> {
        return bleetsRepository.findAll();
    }

    fun findAllBleetsByBleeter(user: String): List<Bleet> {
        return bleetsRepository.findBleetsByBleetUser(user);
    }

    fun processBleet(bleet: Bleet) : Bleet {
        return bleetsRepository.save(bleet);
    }

    private fun getDateTime(s: String): String? {
        try {
            val sdf = SimpleDateFormat("MM/dd/yyyy")
            val netDate = Date((s.toLong()) * 1000)
            return sdf.format(netDate)
        } catch (e: Exception) {
            return e.toString()
        }
    }
}