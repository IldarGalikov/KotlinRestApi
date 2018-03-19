package com.b45.outguess.backend.model

enum class GameTypes(val size: Int, val turns: Int) {
    BASIC5x5(5, 20),
    TEST2x2KILL(2, 4),
    TEST2x2CAPPOINTS(2, 4),
    TEST2x2ALLPOINTS(2, 4)
}

class GameConfigurationFactory {

    companion object {
        fun getGameConfiguration(type: GameTypes): List<Action> {
            val result = mutableListOf<Action>()
            when (type) {
                GameTypes.BASIC5x5 -> {
                    result.addAll(List(3, { Action(ActionTypes.CAPTAIN, requiredUniquePerLocation = true) }))
                    result.addAll(List(5, { Action(ActionTypes.POINTS200) }))
                    result.addAll(List(3, { Action(ActionTypes.POINTS500) }))
                    result.addAll(List(3, { Action(ActionTypes.KILL) }))
                    result.addAll(List(1, { Action(ActionTypes.STEAL) }))
                    result.addAll(List(2, { Action(ActionTypes.SHIELD) }))
                    result.addAll(List(1, { Action(ActionTypes.MIRROR) }))
                    result.addAll(List(1, { Action(ActionTypes.DOUBLE) }))
                    result.addAll(List(25, { Action(ActionTypes.KILL) }))

                }
                GameTypes.TEST2x2KILL -> {
                    result.addAll(List(4, { Action(ActionTypes.KILL) }))
                }
                GameTypes.TEST2x2CAPPOINTS -> {
                    result.addAll(List(2, { Action(ActionTypes.POINTS500) }))
                    result.addAll(List(2, { Action(ActionTypes.CAPTAIN) }))
                }
                GameTypes.TEST2x2ALLPOINTS -> {
                    result.addAll(List(2, { Action(ActionTypes.POINTS500) }))
                    result.addAll(List(2, { Action(ActionTypes.POINTS200) }))
                }
            }
            return result
        }
    }
}