package com.abc.bleeter.bleeterbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Document("bleets")
class Bleet (

    @Id
    val bleetId: String,

    /**
     * The actual Bleet
     */
    val bleetMessage: String,

    /**
     * The person who sent out a bleet.
     */
    val bleetUser: String,

    /**
     * Time at which the bleet was sent out.
     */
    val bleetTimestamp: String
    )