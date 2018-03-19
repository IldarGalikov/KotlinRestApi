package com.b45.outguess.backend.model


enum class ActionTypes(val priority: Int) {

    KILL(2),
    STEAL(3),
    CAPTAIN(100),
    POINTS200(100),
    POINTS500(100),
    DOUBLE(100),
    GO_TO_ZERO(100),
    SHIELD(100),
    MIRROR(100),
    NONE(1000);

    companion object {
        val targetableActions = listOf(
                ActionTypes.KILL,
                ActionTypes.STEAL,
                ActionTypes.CAPTAIN
                )
    }
}

class Action(val action: ActionTypes = ActionTypes.POINTS200,
             val requiredUniquePerLocation: Boolean = false)
