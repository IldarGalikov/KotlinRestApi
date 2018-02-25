package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.model.jpa.GameLobby
import com.b45.outguess.backend.services.LobbyService
import org.springframework.web.bind.annotation.*

@RestController
class LobbyController(val lobbyService: LobbyService) {

    @PostMapping("/lobby")
    fun createLobby(@RequestBody gameLobby: GameLobby) = lobbyService.createLobby(gameLobby);


    @PostMapping("/lobby/{lobbyId}/{userId}")
    fun joinLobby(@PathVariable lobbyId: Long,
                  @PathVariable userId: Long) {

        transformLobbyToResponse(lobbyService.joinLobby(lobbyId, userId))
    }

    @GetMapping("/lobby")
    fun getLobbies(): List<LobbyResponse> = lobbyService
            .getAll()
            .map {transformLobbyToResponse(it)}


    private fun transformLobbyToResponse(gameLobby: GameLobby) =
            LobbyResponse(gameLobby.id,
                    gameLobby.name,
                    gameLobby.users.map { UserResponse(it.id, it.username) }
            )
}

data class LobbyResponse(val lobbyId: Long,
                         val lobbyName: String,
                         val users: List<UserResponse> = ArrayList()
)
