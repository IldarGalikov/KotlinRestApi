package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.jpa.GameLobby
import com.b45.outguess.backend.repositories.GameLobbyRepository
import org.springframework.stereotype.Service

@Service
class LobbyService(val lobbyRepository: GameLobbyRepository,
                   val userService: UserService) {
    fun createLobby(gameLobby: GameLobby): GameLobby = lobbyRepository.saveAndFlush(gameLobby)

    fun joinLobby(lobbyId: Long, userId: Long): GameLobby {
        val user = userService.getUserById(userId)
        val lobby = lobbyRepository.findById(lobbyId)
        user.orElseThrow { NotFoundException("user not found") }
        lobby.orElseThrow { NotFoundException("lobby not found") }

        lobby.get().users.add(user.get())

        return lobbyRepository.saveAndFlush(lobby.get())
    }

    fun getAll(): List<GameLobby> = lobbyRepository.findAll()
    fun findLobbyById(lobbyId: Long) = lobbyRepository.findById(lobbyId)


}
