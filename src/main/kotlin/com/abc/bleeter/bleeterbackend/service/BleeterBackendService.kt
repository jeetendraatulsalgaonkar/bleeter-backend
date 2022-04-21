package com.abc.bleeter.bleeterbackend.service

import com.abc.bleeter.bleeterbackend.model.Bleet
import com.abc.bleeter.bleeterbackend.repository.BleetsRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BleeterBackendService {

    @Autowired
    private lateinit var bleetsRepository: BleetsRepository;

    fun findAllBleets() : List<Bleet> {
        return bleetsRepository.findAll();
    }
}