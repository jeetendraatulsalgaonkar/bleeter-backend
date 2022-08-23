package com.abc.bleeter.bleeterbackend.repository

import com.abc.bleeter.bleeterbackend.model.Bleet
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
open interface BleetsRepository : CrudRepository<Bleet, String> {

    @Query("SELECT b from Bleet b")
    fun findAllBleets(): List<Bleet>

    fun findBleetsByBleetUser(@Param("bleetUser") bleetUser: String): MutableList<Bleet>

    fun deleteBleetsByBleetUserAndBleetMessage(@Param("bleetUser") bleetUser: String, @Param("bleetMessage") bleetMessage: String)
}