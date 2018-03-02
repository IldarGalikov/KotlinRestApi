package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.BadDataException
import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.GameLobby
import com.b45.outguess.backend.model.jpa.GameMap
import org.springframework.stereotype.Service


@Service
class FacadeService(val userService: UsersService,
                    val gamesService: GamesService,
                    val gameMapService: GameMapService,
                    val lobbiesService: LobbiesService,
                    val playerService: PlayerService) {

    fun joinLobby(lobbyId: Long, userId: Long): GameLobby {
        val user = userService.getUserById(userId)
                .orElseThrow { NotFoundException("user not found") }

        return lobbiesService.joinLobby(lobbyId, user)
    }

    fun createGameFromLobby(lobbyId: Long): Game {
        val lobby = lobbiesService.findLobbyById(lobbyId)
                .orElseThrow { NotFoundException("lobby not found") }
        val users = lobby.users
        if (users.size == 0)
            throw BadDataException("Lobby should have positive number of users")
        val gameMaps = gameMapService.generateMapListFromPlayerList(users.size, lobby.gameType)

        val players = users.mapIndexed { index, user -> playerService.createNewPlayerFromUser(user, gameMaps[index]) }
        lobbiesService.deleteLobby(lobby)
        return gamesService.createGame(players, lobby.gameType)
    }
}