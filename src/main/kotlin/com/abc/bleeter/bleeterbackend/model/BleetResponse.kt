package com.abc.bleeter.bleeterbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import lombok.extern.jackson.Jacksonized
import org.springframework.data.annotation.Id
import java.sql.Timestamp

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Jacksonized
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
class BleetResponse (

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