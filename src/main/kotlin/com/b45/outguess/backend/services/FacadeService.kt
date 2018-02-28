package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.BadDataException
import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.GameLobby
import org.springframework.stereotype.Service


@Service
class FacadeService(val userService: UsersService,
                    val gamesService: GamesService,
                    val lobbiesService: LobbiesService,
                    val playerService: PlayerService) {

    fun joinLobby(lobbyId: Long, userId: Long): GameLobby {
        val user = userService.getUserById(userId)
        user.orElseThrow { NotFoundException("user not found") }
        return lobbiesService.joinLobby(lobbyId, user.get())
    }

    fun createGameFromLobby(lobbyId: Long): Game {
        val lobby = lobbiesService.findLobbyById(lobbyId);
        lobby.orElseThrow { NotFoundException("lobby not found") }
        if (lobby.get().users.size == 0)
            throw BadDataException("Lobby should have positive number of users")

        val players = lobby.get().users.map { playerService.createNewPlayerFromUser(it) }

        return Game(players)
    }

}