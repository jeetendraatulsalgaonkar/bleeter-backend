package com.abc.bleeter.bleeterbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.AllArgsConstructor
import lombok.Builder
import lombok.Data
import lombok.NoArgsConstructor
import org.hibernate.annotations.Type
import java.io.Serializable
import java.sql.Timestamp
import javax.persistence.*
import javax.validation.constraints.NotNull

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "BLEET")
class Bleet : Serializable {

    @Id
    @Column(name="ID", nullable = false)
    lateinit var id: String

    /**
     * The actual Bleet
     */
    @NotNull
    @Column(name="BLEETMESSAGE", nullable = false)
    lateinit var bleetMessage: String

    /**
     * The person who sent out a bleet.
     */
    @NotNull
    @Column(name="BLEETUSER", nullable = false)
    lateinit var bleetUser: String

    /**
     *  Image attachment in the bleet. Currently only one image per bleet supported.
     */
    @Lob
    @Type(type="org.hibernate.type.BinaryType")
    @Column(name="BLEETIMAGE", nullable = true)
    var bleetimage: ByteArray? = null

    /**
     * Time at which the bleet was sent out.
     */
    @Column(name="BLEETTIMESTAMP", nullable = true)
    lateinit var bleetTimestamp: Timestamp
}