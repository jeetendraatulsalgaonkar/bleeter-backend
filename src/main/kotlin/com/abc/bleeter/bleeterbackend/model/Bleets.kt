package com.abc.bleeter.bleeterbackend.model

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
class Bleets {

    val bleets: MutableList<Bleet> = mutableListOf<Bleet>();

    fun addBleet(bleet: Bleet) {
        this.bleets.add(bleet)
    }

    fun addAllBleets(bleetList: List<Bleet>) {
        this.bleets.addAll(bleetList)
    }

}