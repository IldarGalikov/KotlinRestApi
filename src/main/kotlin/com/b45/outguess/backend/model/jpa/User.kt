package com.b45.outguess.backend.model.jpa

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
open class User(
        val username: String = "",
        @JsonIgnore
        val email: String = "",
        @Id @GeneratedValue
        val id: Long = -1

)
