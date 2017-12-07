package com.b45.outguess.backend.model

import com.b45.outguess.backend.model.jpa.Player

interface FieldEffect {
    fun applyEffect(player: Player)
}

interface TargetableEffect {
    fun applyEffect(activator: Player, target: Player)
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


//class StealScoreEffect() : FieldEffect,TargetableEffect {
//    override fun applyEffect(activator: Player, target: Player) {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    override fun applyEffect(player: Player) {
//        player.actionToPerform = this
//    }
//}


