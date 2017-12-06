package com.b45.outguess.backend.model


import javax.persistence.*

@Entity
data class Player(

        @OneToOne
        val user: User = User(),
        var score: Int = 0,
        var safeScore: Int = 0,
        var isAlive: Boolean = true,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0
//        @ManyToOne
//        var game: Game = Game()
)