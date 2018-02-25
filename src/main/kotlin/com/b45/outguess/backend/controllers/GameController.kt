package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.services.GameService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class GameController(val gameService: GameService) {

    @GetMapping("/users/{userId}/game")
    fun getGameIdByUserId(@PathVariable userId: Long): GameResponse {
//
//        val currentGame: Game? = usersRepository
//                .findById(userId)
//                .get()
//
//        val id: Long = currentGame?.id ?: -1
//        return GameResponse(id)
        return GameResponse(-1)
    }

    @PostMapping("/game/{lobbyId}")
    fun createGameByLobbyId(@PathVariable lobbyId: Long) =
            gameService.createGameFromLobby(lobbyId)


}

data class GameResponse(val id: Long)