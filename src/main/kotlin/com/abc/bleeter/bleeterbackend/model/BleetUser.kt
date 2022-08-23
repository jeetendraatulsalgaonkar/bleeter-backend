package com.abc.bleeter.bleeterbackend.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import lombok.*
import org.bson.types.ObjectId
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
@Table(name = "BLEETUSER")
class BleetUser : Serializable {

    @Id
    @Column(name="ID", nullable = false)
    lateinit var id: String

    @NotNull
    @Column(name="BLEETUSERNAME", nullable = false)
    lateinit var bleetUsername: String

    @NotNull
    @Column(name="BLEETFIRSTNAME", nullable = false)
    lateinit var bleetFirstname: String

    @NotNull
    @Column(name="BLEETLASTNAME", nullable = false)
    lateinit var bleetLastname: String

    @Column(name="BIO", nullable = true)
    var bio: String? = null

    @Column(name="WEBSITE", nullable = true)
    var website: String? = null

    @Column(name="ACTIVE", nullable = false)
    var active: Boolean = true

    @Column(name="SUSPENDED", nullable = false)
    var suspended: Boolean = false

    @Column(name="CREATEDON", nullable = true)
    lateinit var createdOn: Timestamp

    @Lob
    @Column(name="PROFILEPIC", nullable = true)
    @Type(type="org.hibernate.type.BinaryType")
    val profilepic: ByteArray? = null

    @Column(name="VERIFIED", nullable = false)
    var verified: Boolean = false

}