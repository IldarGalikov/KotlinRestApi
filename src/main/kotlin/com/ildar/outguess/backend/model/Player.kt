package com.ildar.outguess.backend.model


import javax.persistence.*

@Entity
data class Player(

        @OneToOne
        var user: User = User(),
        var score: Int = 0,
        var safeScore: Int = 0,
        var isAlive: Boolean = true,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)