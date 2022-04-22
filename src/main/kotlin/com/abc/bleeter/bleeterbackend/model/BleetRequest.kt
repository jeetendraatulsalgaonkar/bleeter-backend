package com.abc.bleeter.bleeterbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.*
import lombok.extern.jackson.Jacksonized
import org.springframework.data.annotation.Id

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@JsonIgnoreProperties(ignoreUnknown = true)
@Builder
class BleetRequest (

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