package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.services.FacadeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class GameController(val facadeService: FacadeService) {

    @PostMapping("/games/lobby/{lobbyId}")
    fun createGameByLobbyId(@PathVariable lobbyId: Long) = facadeService.createGameFromLobby(lobbyId)

    @GetMapping("/games")
    fun getAllActiveGames() = facadeService.getActiveGames()


    @GetMapping("/games/{gameId}")
    fun getGameById(@PathVariable id: Long) = facadeService.getGameById(id)

//
//    private fun transformGameToGameResponse(game: Game): GameResponse {
//        val players = game.players.map {
//            PlayerResponse(it.id, it.user.username, it.score, it.safeScore, it.isAlive)
//        }
//        return GameResponse(game.id, game.fieldSize, game.currentTurn, game.isActive, players)
//    }
//
}
//data class GameResponse(val gameId: Long,
//                        val fieldSize: Int,
//                        val currentTurn: Int,
//                        val active: Boolean,
//                        val players: List<PlayerResponse>)
//
//data class PlayerResponse(val playerId: Long,
//                          val username: String,
//                          val score: Int,
//                          val safeScore: Int,
//                          val isAlive: Boolean
//)
