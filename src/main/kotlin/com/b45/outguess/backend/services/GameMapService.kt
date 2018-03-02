package com.b45.outguess.backend.services

import com.b45.outguess.backend.model.jpa.GameMap
import com.b45.outguess.backend.model.jpa.MapCell
import com.b45.outguess.backend.repositories.CellsRepository
import com.b45.outguess.backend.repositories.GameMapsRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GameMapService(val gameMapsRepository: GameMapsRepository,
                     val mapCellsRepository: CellsRepository) {

    fun generateMapListFromPlayerList(mapsCount: Int, type: GameTypes): List<GameMap> {
        val configuration = GameConfigurationFactory.getGameConfiguration(type)
        val maps = Array(mapsCount, { Array(type.size, { Array(type.size, { ActionTypes.NONE }) }) })

        configuration
                .sortedBy { a -> a.requiredUniquePerLocation.not() }
                .forEach { currentAction ->
                    maps.forEach {
                        var hasFound = false
                        while (hasFound.not()) {

                            val x = Random().nextInt(type.size)
                            val y = Random().nextInt(type.size)

                            val passUniqueness = currentAction.requiredUniquePerLocation.not() ||
                                    maps.filter { it[x][y] == currentAction.action }.isEmpty()

                            if (it[x][y] == ActionTypes.NONE && passUniqueness) {
                                hasFound = true
                                it[x][y] = currentAction.action
                            }
                        }
                    }
                }

        return maps.map {
            GameMap(cells = this.transformMapToCellList(it).toMutableList(),
                    size = type.size)
        }
    }


    fun transformMapToCellList(map: Array<Array<ActionTypes>>): List<MapCell> =
            map.mapIndexed { x, arrayOfActions ->
                arrayOfActions
                        .mapIndexed { y, type -> MapCell(x, y, type) }
            }.flatten()
}

enum class ActionTypes {
    POINTS200,
    POINTS500,
    KILL,
    STEAL,
    BOMB,
    CAPTAIN,
    SHIELD,
    MIRROR,
    DOUBLE,
    BANK,
    NONE
}

enum class GameTypes(val size: Int) {
    BASIC5x5(5)
}

class Action(val action: ActionTypes = ActionTypes.POINTS200, val requiredUniquePerLocation: Boolean = false)

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
                    result.addAll(List(1, { Action(ActionTypes.SHIELD) }))
                    result.addAll(List(1, { Action(ActionTypes.MIRROR) }))
                    result.addAll(List(1, { Action(ActionTypes.DOUBLE) }))
                    result.addAll(List(3, { Action(ActionTypes.BANK) }))
                }
            }
            return result
        }
    }
}