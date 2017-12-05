package com.ildar.outguess.backend.model

import javax.persistence.*


@Entity
data class Game(
        @OneToMany(fetch = FetchType.EAGER)
        var players: List<Player> = emptyList(),
        val fieldSize: Int = 2,
        var currentTurn: Int = 1,
        var rollX: Int = -1,
        var rollY: Int = -1,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)

