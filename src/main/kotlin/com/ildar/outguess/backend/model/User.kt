package com.ildar.outguess.backend.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id


@Entity
data class User(
        val username: String = "",
        val email: String= "",
        @Id @GeneratedValue
        val id: Long = -1)
