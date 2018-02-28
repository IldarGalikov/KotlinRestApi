package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.jpa.GameLobby
import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.repositories.LobbiesRepository
import org.springframework.stereotype.Service

@Service
class LobbiesService(val lobbiesRepository: LobbiesRepository) {
    fun createLobby(gameLobby: GameLobby): GameLobby = lobbiesRepository.saveAndFlush(gameLobby)
    fun deleteLobby(gameLobby: GameLobby) = lobbiesRepository.delete(gameLobby)


    fun joinLobby(lobbyId: Long, user: User): GameLobby {
        val lobby = lobbiesRepository.findById(lobbyId)
        lobby.orElseThrow { NotFoundException("lobby not found") }

        lobby.get().users.add(user)

        return lobbiesRepository.saveAndFlush(lobby.get())
    }

    fun getAll(): List<GameLobby> = lobbiesRepository.findAll()
    fun findLobbyById(lobbyId: Long) = lobbiesRepository.findById(lobbyId)


}
