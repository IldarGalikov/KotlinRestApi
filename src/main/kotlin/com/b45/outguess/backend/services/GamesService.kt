package com.b45.outguess.backend.services

import com.b45.outguess.backend.exception.hadlers.NotFoundException
import com.b45.outguess.backend.model.ActionTypes
import com.b45.outguess.backend.model.jpa.*
import com.b45.outguess.backend.repositories.GamesRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class GamesService(val gamesRepository: GamesRepository) {

    fun getActiveGames(): List<Game> {
        return gamesRepository.findByIsActive(true).toList()
    }

    fun processActiveGames() {
        val currentTime = LocalDateTime.now()
        val xxx = getActiveGames()
        xxx
                .filter { it.isActive }
                .filter {
                    it.rounds
                    currentTime.isAfter(
                            it.startTime.plusSeconds(it.currentTurn * it.gameSettings.roundTimeout.toLong())
                    )


                }.forEach {
                    calculateNewTurn(it)
                }
    }

    fun getGame(id: Long): Game =
            gamesRepository
                    .findById(id)
                    .orElseThrow { NotFoundException("game not found") }

    fun createGame(players: List<Player>, gameSettings: GameSettings): Game {
        val randomGen = Random()

        val game = Game(players = players, gameSettings = gameSettings)
        game.rounds.add(
                GameRound(
                        rollX = randomGen.nextInt(gameSettings.gameType.size),
                        rollY = randomGen.nextInt(gameSettings.gameType.size)
                )
        )
        return gamesRepository.saveAndFlush(game)
    }


    fun findGameByUserId(userId: Long) =
            gamesRepository
                    .findByPlayersUserUserId(userId)
                    .orElseThrow { NotFoundException("game not found") }

    fun calculateNewTurn(game: Game) {
        println("Calculating new turn")
        val rounds = game.rounds
        val round = rounds[game.currentTurn]

        val allActionsSortedByPriority = ActionTypes
                .values()
                .sortedBy { it.priority }

        allActionsSortedByPriority.forEach {
            resolveCurrentActionInGameRound(game, round, it)
        }

        game.currentTurn++
        if(game.currentTurn == game.gameSettings.gameType.turns - 1)
            game.isActive = false
        else {
            //needed if captain has added new round already
            if (game.rounds.size < game.currentTurn)
                game.rounds.add(generateNewRound(game))
        }
        gamesRepository.saveAndFlush(game)
    }

    private fun resolveCurrentActionInGameRound(game: Game, round: GameRound, currentAction: ActionTypes) {
        findPlayersWithCurrentActionInThisRound(game, round, currentAction).forEach { player ->
            val playerActionsInThisRound =round.playerActions.filter {
                player == it.player
            }
            if(playerActionsInThisRound.isEmpty() && ActionTypes.targetableActions.contains(currentAction))
                logUserHasSkippedHisTurn(player)
            else resolvePlayerAction(game, currentAction, playerActionsInThisRound.first())

        }
    }

    private fun logUserHasSkippedHisTurn(player: Player) {
        println(" ${player.user.username} has skipped the turn")
    }

    private fun findPlayersWithCurrentActionInThisRound(game: Game, round: GameRound, currentAction: ActionTypes) =
            game.players.filter {
                it.gameMap
                        .cells.first { it.x == round.rollX && it.y == round.rollY }
                        .type == currentAction
            }

    private fun resolvePlayerAction(game: Game, actionType: ActionTypes, playerAction: PlayerAction) {

        when (actionType) {
            ActionTypes.POINTS200 -> playerAction.player.score += 200
            ActionTypes.POINTS500 -> playerAction.player.score += 500
            ActionTypes.DOUBLE -> playerAction.player.score *= 2
            ActionTypes.GO_TO_ZERO -> playerAction.player.score = 0
            ActionTypes.MIRROR -> playerAction.player.items.add(InventoryItem(ActionTypes.MIRROR))
            ActionTypes.SHIELD -> playerAction.player.items.add(InventoryItem(ActionTypes.SHIELD))
            ActionTypes.KILL -> playerAction.targetPlayer!!.isAlive = false
            ActionTypes.STEAL -> {
                playerAction.player.score += playerAction.targetPlayer!!.score
                playerAction.targetPlayer.score = 0
            }
            ActionTypes.CAPTAIN -> game.rounds.add(GameRound(rollX = playerAction.targetCell!!.x, rollY = playerAction.targetCell.y))
            else -> {
            }
        }
    }
    fun getRoundByGameId(gameId: Long): GameRound {
        val game = getGame(gameId)
        return game.rounds[game.currentTurn]
    }

    fun getTurnByUserId(userId: Long): MapCell {
        val game = findGameByUserId(userId)
        val round = game.rounds[game.currentTurn]
        val gameMap = game.players.first { it.user.userId == userId }.gameMap
        return gameMap.cells.first { it.x == round.rollX && it.y == round.rollY }
    }

    fun updateGame(game: Game) =
            gamesRepository.saveAndFlush(game)

    fun generateNewRound(game: Game) : GameRound {
//        if (game.)

        val randomGen = Random()
        while (true) {
            val rollX = randomGen.nextInt(game.gameSettings.gameType.size)
            val rollY = randomGen.nextInt(game.gameSettings.gameType.size)

            if(game.rounds.none { it.rollX == rollX && it.rollY == rollY })
                return GameRound(rollX = rollX, rollY = rollY)
        }

    }


}