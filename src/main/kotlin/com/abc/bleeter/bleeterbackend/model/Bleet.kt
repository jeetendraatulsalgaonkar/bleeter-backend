package com.abc.bleeter.bleeterbackend.model

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import java.sql.Timestamp

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
class Bleet (

    /**
     * The actual Bleet
     */
    val bleetMessage: String,

    /**
     * The person who sent out a bleet.
     */
    val bleeter: String,

    /**
     * Time at which the bleet was sent out.
     */
    val time: Timestamp
    )