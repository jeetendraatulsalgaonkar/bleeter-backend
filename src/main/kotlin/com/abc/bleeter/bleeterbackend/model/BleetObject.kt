package com.abc.bleeter.bleeterbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.*
import org.springframework.data.annotation.Id

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class BleetObject {

    @Id
    lateinit var id: String

    @NonNull
    lateinit var bleetMessage: String

    @NonNull
    lateinit var bleetUser: String

    @NonNull
    lateinit var bleetFirstname: String

    @NonNull
    lateinit var bleetLastname: String

    var profilepic: ByteArray? = null

    var bleetimage: ByteArray? = null

    @NonNull
    lateinit var bleetTimestamp: String

    @NonNull
    var verified: Boolean = false
}