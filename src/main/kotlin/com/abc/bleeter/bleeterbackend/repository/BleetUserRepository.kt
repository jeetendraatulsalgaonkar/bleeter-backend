package com.abc.bleeter.bleeterbackend.repository

import com.abc.bleeter.bleeterbackend.model.BleetUser
import org.springframework.data.repository.CrudRepository

open interface BleetUserRepository : CrudRepository<BleetUser, String> {

    fun findBleetUserByBleetUsername(bleetUsername: String) : BleetUser
}