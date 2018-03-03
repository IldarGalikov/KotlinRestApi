package com.b45.outguess.backend.background

import com.b45.outguess.backend.services.GamesService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.util.*


@Component
class ScheduledTasks(val gamesService: GamesService) {

    @Scheduled(fixedRate = 2000)
    fun reportCurrentTime() {
        log.info("Processing games : {}", LocalDateTime.now().toString())
        val currentTime = LocalDateTime.now()
        val randomGen = Random()
        gamesService.getActiveGames()
                .filter {
                    currentTime.isAfter(
                            it.startTime
                                    .plusSeconds(it.currentTurn * it.gameSettings.roundTimeout.toLong())
                    )
                }.forEach {
                    it.currentTurn.inc()

                }

    }

    companion object {
        private val log = LoggerFactory.getLogger(ScheduledTasks::class.java)
    }
}