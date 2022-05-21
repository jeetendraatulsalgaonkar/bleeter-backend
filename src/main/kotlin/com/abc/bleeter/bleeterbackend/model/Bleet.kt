package com.abc.bleeter.bleeterbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import javax.validation.constraints.NotNull

@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
@Document("bleets")
@NoArgsConstructor
class Bleet (

    @Id
    val id: String,

    /**
     * The actual Bleet
     */
    @NotNull
    val bleetMessage: String,

    /**
     * The person who sent out a bleet.
     */
    @NotNull
    val bleetUser: String,

    /**
     * Time at which the bleet was sent out.
     */
    @NotNull
    val bleetTimestamp: String
    )