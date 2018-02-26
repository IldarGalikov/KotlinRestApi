package com.b45.outguess.backend.model.jpa

import javax.persistence.*

@Entity
data class Player(
        @ManyToOne
        val user: User = User(),
        var score: Int = 0,
        var safeScore: Int = 0,
        var isAlive: Boolean = true,
        @Id @GeneratedValue
        val id: Long = -1
)