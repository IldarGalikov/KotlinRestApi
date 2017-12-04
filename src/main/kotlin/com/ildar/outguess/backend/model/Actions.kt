package com.ildar.outguess.backend.model

import java.lang.reflect.Field

interface FieldEffect {
    fun applyEffect(player: Player)
}

interface TargetableEffect {
    fun applyEffect(activator: Player, target: Player)
}

class Player(val user: User, val game: Game) {

    var score: Int = 0
    var safeScore: Int = 0
    var isAlive: Boolean = true
    //val map: Array<Array<FieldEffect>> = Array()
    fun generateMap() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    var actionToPerform: StealScoreEffect? = null
//    init {
//        map
//    }
}


class NoneEffect() : FieldEffect {
    override fun applyEffect(player: Player) {
    }
}

class AddScoreEffect(val scoreValue: Int) : FieldEffect {
    override fun applyEffect(player: Player) {
        player.score += scoreValue
    }
}

class SaveMoneyEffect(val scoreValue: Int) : FieldEffect {
    override fun applyEffect(player: Player) {
        player.safeScore += scoreValue
        player.score = 0;
    }
}

class KillPlayerEffect() : TargetableEffect {
    override fun applyEffect(activator: Player, target: Player) {
        target.isAlive = false
    }

}


class StealScoreEffect() : FieldEffect,TargetableEffect {
    override fun applyEffect(activator: Player, target: Player) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun applyEffect(player: Player) {
        player.actionToPerform = this
    }
}


