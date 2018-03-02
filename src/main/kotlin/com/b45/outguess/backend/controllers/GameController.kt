package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.model.jpa.GameMap
import com.b45.outguess.backend.model.jpa.MapCell
import com.b45.outguess.backend.repositories.CellsRepository
import com.b45.outguess.backend.repositories.GameMapsRepository
import com.b45.outguess.backend.services.ActionTypes
import com.b45.outguess.backend.services.FacadeService
import com.b45.outguess.backend.services.GameMapService
import com.b45.outguess.backend.services.GamesService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class GameController(val gamesService: GamesService,
                     val facadeService: FacadeService,
                     val gameMapService: GameMapService) {

    @PostMapping("/games/lobby/{lobbyId}")
    fun createGameByLobbyId(@PathVariable lobbyId: Long) = facadeService.createGameFromLobby(lobbyId)

    @GetMapping("/games")
    fun getAllActiveGames() = gamesService.getActiveGames()


    @GetMapping("/games/{gameId}")
    fun getGameById(@PathVariable id: Long) = gamesService.getGame(id)

}

