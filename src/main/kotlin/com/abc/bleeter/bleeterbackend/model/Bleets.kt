package com.abc.bleeter.bleeterbackend.model

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.Setter

@Getter
@Setter
@AllArgsConstructor
class Bleets {

    val bleetObjects: MutableList<BleetObject> = mutableListOf<BleetObject>();

    fun addBleetObject(bleetObject: BleetObject) {
        this.bleetObjects.add(bleetObject)
    }

    fun addAllBleetObjects(bleetObjectList: List<BleetObject>) {
        this.bleetObjects.addAll(bleetObjectList)
    }

}