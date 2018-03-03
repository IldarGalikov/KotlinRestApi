package com.b45.outguess.backend.services

import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.GameMap
import com.b45.outguess.backend.model.jpa.Player
import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.repositories.CellsRepository
import com.b45.outguess.backend.repositories.GameMapsRepository
import com.b45.outguess.backend.repositories.PlayersRepository
import org.springframework.stereotype.Service

@Service
class PlayerService(val playersRepository: PlayersRepository) {

    fun createNewPlayerFromUser(user: User, gameMap: GameMap): Player {

        return playersRepository.saveAndFlush(Player(user = user, gameMap = gameMap))
    }

}
