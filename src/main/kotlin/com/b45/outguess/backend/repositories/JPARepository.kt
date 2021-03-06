package com.b45.outguess.backend.repositories

import com.b45.outguess.backend.model.jpa.*
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UsersRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Iterable<User>
}

interface GamesRepository : JpaRepository<Game, Long> {
    fun findByPlayersUserUserId(userId: Long) : Optional<Game>
    fun findByIsActive(b: Boolean): Iterable<Game>
}

interface PlayersRepository : JpaRepository<Player, Long>
interface LobbiesRepository : JpaRepository<GameLobby, Long>
interface GameMapsRepository : JpaRepository<GameMap, Long>
interface CellsRepository : JpaRepository<MapCell, Long>


