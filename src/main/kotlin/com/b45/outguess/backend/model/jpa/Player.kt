package com.b45.outguess.backend.model.jpa

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*

@Entity
data class Player(
        @ManyToOne
        @JsonIgnore
        val user: User = User(),
        var score: Int = 0,
        var safeScore: Int = 0,
        var isAlive: Boolean = true,
        @Id @GeneratedValue
        val playerId: Long = -1,
        @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        val gameMap: GameMap = GameMap(),
        @OneToMany
        val items: MutableList<InventoryItem> = mutableListOf()
) {
    @Transient
    @JsonProperty(value = "username")
    fun getUsername() = user.username
}