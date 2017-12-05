package com.ildar.outguess.backend.repositories

import com.ildar.outguess.backend.model.Game
import com.ildar.outguess.backend.model.Player
import com.ildar.outguess.backend.model.User
import org.springframework.data.repository.CrudRepository

interface UsersRepository : CrudRepository<User, Long> {

    fun findByEmail(email: String): Iterable<User>
}

interface GamesRepository : CrudRepository<Game, Long> {

}

interface PlayersRepository : CrudRepository<Player, Long> {

}
