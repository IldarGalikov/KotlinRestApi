package com.b45.outguess.backend.model.jpa

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import javax.persistence.*
import kotlin.jvm.Transient

@Entity
data class Player(
        @OneToOne
        @JsonIgnore
        val user: User = User(),
        @Transient
        @JsonProperty(value = "username")
        val username:String = user.username,
        var score: Int = 0,
        var safeScore: Int = 0,
        var isAlive: Boolean = true,
        @Id @GeneratedValue
        val id: Long = -1,
        @OneToOne
        val gameMap: GameMap = GameMap()
)