package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.BadDataException
import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.ActionTypes
import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.GameLobby
import com.b45.outguess.backend.model.jpa.PlayerAction
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
            throw BadDataException("Lobby should have at least 1 user")
        val gameMaps = gameMapService.generateMapListFromPlayerList(users.size, lobby.gameSettings.gameType)

        val players = users.mapIndexed { index, user -> playerService.createNewPlayerFromUser(user, gameMaps[index]) }
        lobbiesService.deleteLobby(lobby)
        return gamesService.createGame(players, lobby.gameSettings)
    }

    fun registerPlayerTarget(userId: Long, targetId: Long) {
        val game = gamesService.findGameByUserId(userId)

        val targetPlayerList = game.players.filter { it.playerId == targetId }

        if (targetPlayerList.isEmpty()) {
            throw NotFoundException("There is no such target player in current game")
        } else {
            val userPlayer = game.players.first { it.user.userId == userId }
            val targetPlayer = targetPlayerList.first()
            val turn = gamesService.getTurnByUserId(userId)

            if (turn.type != ActionTypes.KILL || turn.type != ActionTypes.STEAL)
                BadDataException("${turn.type.name} does not require playerTarget")

            game.rounds[game.currentTurn].playerActions.add(
                    PlayerAction(player = userPlayer, targetPlayer = targetPlayer, targetCell = null)
            )
            gamesService.updateGame(game)

        }


    }
}