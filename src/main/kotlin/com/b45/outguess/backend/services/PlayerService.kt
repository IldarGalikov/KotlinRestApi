package com.b45.outguess.backend.services

import com.b45.outguess.backend.model.jpa.Player
import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.repositories.PlayersRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(val playersRepository: PlayersRepository) {

    fun createNewPlayerFromUser(user: User) =
            playersRepository.saveAndFlush(Player(user))
}
