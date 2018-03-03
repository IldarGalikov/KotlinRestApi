package com.b45.outguess.backend.model.jpa

import com.b45.outguess.backend.services.GameTypes
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Game(
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val players: List<Player>,
        val gameType: GameTypes,
        val currentTurn: Int = 1,
        val rollX: Int = -1,
        val rollY: Int = -1,
        val isActive: Boolean = true,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val gameId: Long = -1,
        val startTime: LocalDateTime = LocalDateTime.now()
)
