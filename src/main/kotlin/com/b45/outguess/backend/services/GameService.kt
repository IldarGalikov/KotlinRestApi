package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.repositories.GamesRepository
import org.springframework.stereotype.Service

@Service
class GameService(val gamesRepository: GamesRepository,
                  val lobbyService: LobbyService) {
    companion object {
        val MAX_ROUNDS = 2
        val MAX_PLAYERS = 2
        val MAX_MAP_SIZE = 2
    }

    fun getActiveGames() = gamesRepository.findByIsActive(true)

    fun createGameFromLobby(lobbyId: Long) {
        val lobby = lobbyService.findLobbyById(lobbyId)
        lobby.orElseThrow { NotFoundException("lobby not found") }


    }

}