package com.b45.outguess.backend.background

import com.b45.outguess.backend.services.GamesService
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat
import java.time.LocalDateTime


@Component
class ScheduledTasks(val gamesService: GamesService) {

    @Scheduled(fixedRate = 2000)
    fun reportCurrentTime() {
        log.info("The time is now {}",LocalDateTime.now().toString())
        gamesService.getActiveGames()

    }

    companion object {
        private val log = LoggerFactory.getLogger(ScheduledTasks::class.java)
    }
}