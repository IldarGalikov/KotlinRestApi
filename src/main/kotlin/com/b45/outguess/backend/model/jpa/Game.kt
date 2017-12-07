package com.b45.outguess.backend.model.jpa

import javax.persistence.*


@Entity
data class Game(
        @OneToMany(fetch = FetchType.EAGER)
        var players: List<Player> = emptyList(),
        val fieldSize: Int = 2,
        var currentTurn: Int = 1,
        var rollX: Int = -1,
        var rollY: Int = -1,
        var isActive: Boolean = true,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long = 0
)

