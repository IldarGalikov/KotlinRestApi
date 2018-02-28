package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.model.jpa.GameLobby
import com.b45.outguess.backend.services.FacadeService
import com.b45.outguess.backend.services.LobbiesService
import org.springframework.web.bind.annotation.*

@RestController
class LobbyController(val facadeService: FacadeService,
                      val lobbiesService: LobbiesService) {

    @PostMapping("/lobby")
    fun createLobby(@RequestBody gameLobby: GameLobby) = lobbiesService.createLobby(gameLobby)


    @PostMapping("/lobby/{lobbyId}/{userId}")
    fun joinLobby(@PathVariable lobbyId: Long,
                  @PathVariable userId: Long) = facadeService.joinLobby(lobbyId, userId)

    @GetMapping("/lobby")
    fun getLobbies(): List<GameLobby> = lobbiesService.getAll()


}

