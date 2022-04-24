package com.abc.bleeter.bleeterbackend.repository

import com.abc.bleeter.bleeterbackend.model.Bleet
import org.springframework.data.domain.Example
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface BleetsRepository : MongoRepository<Bleet, String> {
    override fun <S : Bleet> save(entity: S): S

    // override fun findById(id: String): Optional<Bleet>

    override fun findAll(): MutableList<Bleet>

    override fun count(): Long

    // override fun deleteById(id: String)

    override fun delete(entity: Bleet)

    override fun deleteAll()

    override fun <S : Bleet> exists(example: Example<S>): Boolean

    // TODO: Rectify
    @Query("{'bleetUser' : :#{#bleetUser}}")
    fun findBleetsByBleetUser(@Param("bleetUser") bleetUser: String): MutableList<Bleet>
}