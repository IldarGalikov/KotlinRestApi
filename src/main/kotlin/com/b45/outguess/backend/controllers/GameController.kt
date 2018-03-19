package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.model.jpa.GameRound
import com.b45.outguess.backend.model.jpa.MapCell
import com.b45.outguess.backend.model.jpa.PlayerAction
import com.b45.outguess.backend.services.FacadeService
import com.b45.outguess.backend.services.GamesService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*


@RestController
class GameController(val gamesService: GamesService,
                     val facadeService: FacadeService) {

    @PostMapping("/games/lobby/{lobbyId}")
    fun createGameByLobbyId(@PathVariable lobbyId: Long) = facadeService.createGameFromLobby(lobbyId)

    @GetMapping("/games")
    fun getAllActiveGames() = gamesService.getActiveGames()

    @GetMapping("/games/{gameId}")
    fun getGameById(@PathVariable gameId: Long) = gamesService.getGame(gameId)

    @GetMapping("/games/user/{userId}")
    fun getGameByUserId(@PathVariable userId: Long) = gamesService.findGameByUserId(userId)

    @GetMapping("/games/{gameId}/round")
    fun getCurrentRoundOfTheGame(@PathVariable gameId: Long): GameRound =
            gamesService.getRoundByGameId(gameId)

    @GetMapping("/games/user/{userId}/turn")
    fun getCurrentTurnByUserId(@PathVariable userId: Long): MapCell =
            gamesService.getTurnByUserId(userId)

    @PostMapping("/games/user/{userId}/playerTarget/{targetId}")
    @ResponseStatus(HttpStatus.CREATED)
    fun postTurnAction(@PathVariable userId: Long,
                       @PathVariable targetId: Long) =
            facadeService.registerPlayerTarget(userId, targetId)

    @GetMapping("/dummy")
    fun getXXXX() = gamesService.getActiveGames()


}

