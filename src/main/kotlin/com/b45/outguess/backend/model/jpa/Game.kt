package com.b45.outguess.backend.model.jpa

import javax.persistence.*


@Entity
data class Game(
        @OneToMany(fetch = FetchType.EAGER)
        val players: List<Player> = emptyList(),
        val fieldSize: Int = 2,
        val currentTurn: Int = 1,
        val rollX: Int = -1,
        val rollY: Int = -1,
        val isActive: Boolean = true,
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long = 0
)
