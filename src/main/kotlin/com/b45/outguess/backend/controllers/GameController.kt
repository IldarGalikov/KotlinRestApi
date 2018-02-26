package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.services.GameService
import org.springframework.web.bind.annotation.*


@RestController
class GameController(val gameService: GameService) {

    @PostMapping("/games/lobby/{lobbyId}")
    fun createGameByLobbyId(@PathVariable lobbyId: Long) : GameResponse {
        val game = gameService.createGameFromLobby(lobbyId)
        return transformGameToGameResponse(game)
    }

    @GetMapping("/games")
    fun getAllActiveGames() : List<GameResponse> {
        return gameService.getActiveGames().map { transformGameToGameResponse(it) }
    }

    @GetMapping("/games/{gameId}")
    fun getGameById(@PathVariable id: Long) :GameResponse {
        return transformGameToGameResponse(gameService.getGame(id))
    }

    private fun transformGameToGameResponse(game: Game): GameResponse {
        val players = game.players.map {
            PlayerResponse(it.id, it.user.username, it.score, it.safeScore, it.isAlive)
        }
        return GameResponse(game.id, game.fieldSize, game.currentTurn, game.isActive, players)
    }
}

data class GameResponse(val gameId: Long,
                        val fieldSize: Int,
                        val currentTurn: Int,
                        val active: Boolean,
                        val players: List<PlayerResponse>)

data class PlayerResponse(val playerId: Long,
                          val username: String,
                          val score: Int,
                          val safeScore: Int,
                          val isAlive: Boolean
)
