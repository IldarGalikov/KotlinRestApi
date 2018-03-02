package com.b45.outguess.backend.services

import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.Player
import com.b45.outguess.backend.repositories.GamesRepository
import org.springframework.stereotype.Service

@Service
class GamesService(val gamesRepository: GamesRepository) {
    companion object {
        val MAX_ROUNDS = 2
        val MAX_PLAYERS = 2
        val MAX_MAP_SIZE = 2
    }

    fun getActiveGames() =  gamesRepository.findByIsActive(true).toList()

    fun getGame(id: Long): Game = gamesRepository.findById(id).get()
    fun createGame(players: List<Player>): Game = gamesRepository.saveAndFlush(Game(players))



}