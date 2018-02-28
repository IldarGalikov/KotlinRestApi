package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.jpa.GameLobby
import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.repositories.GameLobbyRepository
import org.springframework.stereotype.Service

@Service
class LobbyService(val lobbyRepository: GameLobbyRepository) {
    fun createLobby(gameLobby: GameLobby): GameLobby = lobbyRepository.saveAndFlush(gameLobby)
    fun deleteLobby(gameLobby: GameLobby) = lobbyRepository.delete(gameLobby)

    fun joinLobby(lobbyId: Long, user: User): GameLobby {
        val lobby = lobbyRepository.findById(lobbyId)
        lobby.orElseThrow { NotFoundException("lobby not found") }

        lobby.get().users.add(user)

        return lobbyRepository.saveAndFlush(lobby.get())
    }

    fun getAll(): List<GameLobby> = lobbyRepository.findAll()
    fun findLobbyById(lobbyId: Long) = lobbyRepository.findById(lobbyId)


}
