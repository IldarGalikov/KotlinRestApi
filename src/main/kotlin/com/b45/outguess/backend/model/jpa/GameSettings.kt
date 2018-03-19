package com.b45.outguess.backend.model.jpa

import com.b45.outguess.backend.model.GameTypes
import javax.persistence.*

@Embeddable
data class GameSettings (
        val gameType : GameTypes = GameTypes.BASIC5x5,
        val roundTimeout: Int = 30,
        val maxPlayers : Int = 20
)