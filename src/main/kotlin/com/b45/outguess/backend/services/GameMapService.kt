package com.b45.outguess.backend.services

import com.b45.outguess.backend.model.Action
import com.b45.outguess.backend.model.ActionTypes
import com.b45.outguess.backend.model.GameConfigurationFactory
import com.b45.outguess.backend.model.GameTypes
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
                .forEach { currentAction -> generateActionForMaps(maps, currentAction) }

        return maps.map {
            GameMap(cells = this.transformMapToCellList(it))
        }
    }


    fun generateActionForMaps(maps: Array<Array<Array<ActionTypes>>>,
                              currentAction: Action) {
        val randomGen = Random()
        maps.forEach {
            var hasFound = false
            while (hasFound.not()) {
                val x = randomGen.nextInt(it.size)
                val y = randomGen.nextInt(it.size)

                val passUniqueness = currentAction.requiredUniquePerLocation.not() ||
                        maps.filter { it[x][y] == currentAction.action }.isEmpty()

                if (it[x][y] == ActionTypes.NONE && passUniqueness) {
                    hasFound = true
                    it[x][y] = currentAction.action
                }
            }
        }
    }


    fun transformMapToCellList(map: Array<Array<ActionTypes>>): MutableList<MapCell> =
            map.mapIndexed { x, arrayOfActions ->
                arrayOfActions
                        .mapIndexed { y, type -> MapCell(x, y, type) }
            }.flatten().toMutableList()
}


