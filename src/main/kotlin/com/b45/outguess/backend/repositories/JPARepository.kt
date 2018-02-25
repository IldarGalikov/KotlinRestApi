package com.b45.outguess.backend.repositories

import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.GameLobby
import com.b45.outguess.backend.model.jpa.Player
import com.b45.outguess.backend.model.jpa.User
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository : JpaRepository<User, Long> {

    fun findByEmail(email: String): Iterable<User>

}

interface GamesRepository : JpaRepository<Game, Long> {
    fun findByIsActive(active: Boolean) : Iterable<Game>

}

interface PlayersRepository : JpaRepository<Player, Long>

interface GameLobbyRepository : JpaRepository<GameLobby, Long>

