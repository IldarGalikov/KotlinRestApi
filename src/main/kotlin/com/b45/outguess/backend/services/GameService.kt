package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.repositories.GamesRepository
import org.springframework.stereotype.Service

@Service
class GameService(val gamesRepository: GamesRepository,
                  val lobbyService: LobbyService,
                  val matchPlayerService: PlayerService) {
    companion object {
        val MAX_ROUNDS = 2
        val MAX_PLAYERS = 2
        val MAX_MAP_SIZE = 2
    }

    fun getActiveGames() = gamesRepository.findByIsActive(true)

    fun createGameFromLobby(lobbyId: Long): Game {
        val lobby = lobbyService.findLobbyById(lobbyId)
        lobby.orElseThrow { NotFoundException("lobby not found") }
        val players = lobby.get().users.map {
            matchPlayerService.createNewPlayerFromUser(it)
        }
        lobbyService.deleteLobby(lobby.get())
        val game = Game(players = players, fieldSize = MAX_MAP_SIZE, isActive = true)

        return gamesRepository.saveAndFlush(game)
    }

    fun getGame(id: Long): Game = gamesRepository.findById(id).get()


}