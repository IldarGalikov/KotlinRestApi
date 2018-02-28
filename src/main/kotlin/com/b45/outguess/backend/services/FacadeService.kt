package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.BadDataException
import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.GameLobby
import org.springframework.stereotype.Service


@Service
class FacadeService(val userService: UserService,
                    val gameService: GameService,
                    val lobbyService: LobbyService,
                    val playerService: PlayerService) {

    fun createLobby(gameLobby: GameLobby) = lobbyService.createLobby(gameLobby)
    fun joinLobby(lobbyId: Long, userId: Long): GameLobby {
        val user = userService.getUserById(userId)
        user.orElseThrow { NotFoundException("user not found") }
        return lobbyService.joinLobby(lobbyId, user.get())
    }

    fun getAllLobbies(): List<GameLobby> = lobbyService.getAll()
    fun createGameFromLobby(lobbyId: Long): Game {
        val lobby = lobbyService.findLobbyById(lobbyId);
        lobby.orElseThrow { NotFoundException("lobby not found") }
        if (lobby.get().users.size == 0)
            throw BadDataException("Lobby should have positive number of users")
        val players = lobby.get().users.map { playerService.createNewPlayerFromUser(it) }

        return Game(players)
    }

    fun getActiveGames(): List<Game> = gameService.getActiveGames()
    fun getGameById(id: Long) = gameService.getGame(id)


}