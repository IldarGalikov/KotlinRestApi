package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.GameSettings
import com.b45.outguess.backend.model.jpa.Player
import com.b45.outguess.backend.repositories.GamesRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GamesService(val gamesRepository: GamesRepository) {
    companion object {
        val MAX_ROUNDS = 2
        val MAX_PLAYERS = 2
        val MAX_MAP_SIZE = 2
    }

    fun getActiveGames() =  gamesRepository.findByIsActive(true).toList()

    fun getGame(id: Long): Game =
            gamesRepository
                    .findById(id)
                    .orElseThrow { NotFoundException("game not found") }

    fun createGame(players: List<Player>, gameSettings: GameSettings) =
            gamesRepository.saveAndFlush(Game(players,
                    gameSettings = gameSettings,
                    rollX = Random().nextInt(gameSettings.gameType.size),
                    rollY = Random().nextInt(gameSettings.gameType.size)))

    fun findGameByUserId(userId: Long) =
            gamesRepository
                    .findByPlayersUserUserId(userId)
                    .orElseThrow { NotFoundException("game not found") }



}