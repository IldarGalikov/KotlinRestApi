package com.b45.outguess.backend.services

import com.b45.outguess.backend.model.jpa.Player
import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.repositories.PlayersRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(val matchPlayersRepository: PlayersRepository) {

    fun createNewPlayerFromUser(user: User): Player {
        val player = Player(user)
        return matchPlayersRepository.saveAndFlush(player)
    }
}
