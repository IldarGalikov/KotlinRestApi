package com.b45.outguess.backend.model.jpa

import com.b45.outguess.backend.services.GameTypes
import javax.persistence.*

@Entity
data class GameLobby(@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val lobbyId: Long = 0,
                     @OneToMany
                     var users: MutableList<User> = ArrayList(),
                     val name: String,
                     val gameType: GameTypes = GameTypes.BASIC5x5)
