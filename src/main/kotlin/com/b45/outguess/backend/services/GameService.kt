package com.b45.outguess.backend.services

import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.Player
import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.repositories.GamesRepository
import org.springframework.stereotype.Service

@Service
class GameService(private val gamesRepository: GamesRepository){
    companion object {
        val MAX_ROUNDS = 2
        val MAX_PLAYERS = 2
        val MAX_MAP_SIZE = 2
    }
    fun getActiveGames() = gamesRepository.findByIsActive(true)

    fun createGame(users:Iterable<User>){

        val game = Game(users.map { u -> Player(user = u) }, fieldSize = MAX_MAP_SIZE)
    }
}