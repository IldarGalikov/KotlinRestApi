package com.b45.outguess.backend.model.jpa

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import org.hibernate.annotations.Fetch
import org.hibernate.annotations.FetchMode
import java.time.LocalDateTime
import javax.persistence.*


@Entity
data class Game(
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val players: List<Player>,
        var currentTurn: Int = 0,
        @OneToMany(cascade = [CascadeType.ALL], fetch = FetchType.EAGER)
        @Fetch(value = FetchMode.SUBSELECT)
        val rounds: MutableList<GameRound> = mutableListOf(),
        var isActive: Boolean = true,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val gameId: Long = -1,
        val startTime: LocalDateTime = LocalDateTime.now(),
        @Embedded
        val gameSettings: GameSettings = GameSettings()
)


@Entity
data class GameRound(
        val rollX: Int = -1,
        val rollY: Int = -1,
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
//        @JsonIgnore
        val playerActions: MutableList<PlayerAction> = mutableListOf(),
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val gameRoundId: Long = -1
)

@Entity
data class PlayerAction(
        @ManyToOne
        @JsonIgnore
        val player: Player,
        @ManyToOne
        @JsonIgnore
        val targetPlayer: Player?,
        @ManyToOne
        @JsonIgnore
        val targetCell: MapCell?,
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        val playerActionId: Long = -1
) {
    @Transient
    @JsonProperty(value = "playerId")
    fun getPlayerId(): Long = player.playerId

    @Transient
    @JsonProperty(value = "targetPlayerId")
    fun getTagetPlayerId(): Long = targetPlayer?.playerId ?: -1

    @Transient
    @JsonProperty(value = "cellId")
    fun getCellId(): Long = targetCell?.cellId ?: -1
}
