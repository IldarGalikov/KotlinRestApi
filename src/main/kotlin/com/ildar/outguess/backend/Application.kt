package com.ildar.outguess.backend

import com.ildar.outguess.backend.model.Game
import com.ildar.outguess.backend.model.Player
import com.ildar.outguess.backend.model.User
import com.ildar.outguess.backend.repositories.GamesRepository
import com.ildar.outguess.backend.repositories.PlayersRepository
import com.ildar.outguess.backend.repositories.UsersRepository
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class Application {

    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    fun init(usersRepository: UsersRepository,
             gamesRepository: GamesRepository,
             playersRepository: PlayersRepository) = CommandLineRunner {


        usersRepository.save(User("Jack", "Bauer"))
        usersRepository.save(User("Chloe", "O'Brian"))
        usersRepository.save(User("Kim", "Bauer"))
        usersRepository.save(User("David", "Palmer"))
        usersRepository.save(User("Michelle", "Dessler"))

        log.info("users found with findAll():")
        log.info("-------------------------------")
        usersRepository.findAll().forEach { log.info(it.toString()) }
        log.info("\n\n")

        val players = usersRepository.findAll().map { u -> Player(user = u) }
        playersRepository.saveAll(players)

        log.info("players found with findAll():")
        log.info("-------------------------------")
        playersRepository.findAll().forEach { log.info(it.toString()) }
        log.info("\n\n")

        gamesRepository.save(Game(playersRepository.findAll().toList()))

        log.info("games found with findAll():")
        log.info("-------------------------------")
        gamesRepository.findAll().forEach { log.info(it.toString()) }
        log.info("\n\n")


    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
