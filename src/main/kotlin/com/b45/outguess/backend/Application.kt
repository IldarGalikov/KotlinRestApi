package com.b45.outguess.backend

import com.b45.outguess.backend.model.jpa.Game
import com.b45.outguess.backend.model.jpa.Player
import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.repositories.GamesRepository
import com.b45.outguess.backend.repositories.PlayersRepository
import com.b45.outguess.backend.repositories.UsersRepository
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
        usersRepository.flush()
        log.info("users found with findAll():")
        log.info("-------------------------------")
        usersRepository.findAll().forEach { log.info(it.toString()) }
        log.info("\n\n")

//
//        playersRepository.saveAll(usersRepository.findAll().map { Player(it) }.toList())
//
//        playersRepository.flush()
//        log.info("players found with findAll():")
//        log.info("-------------------------------")
//        playersRepository.findAll().forEach { log.info(it.toString()) }
//        log.info("\n\n")
//
//
//
//
//        gamesRepository.save(Game(playersRepository.findAll().toList()))
//
//        log.info("games found with findAll():")
//        log.info("-------------------------------")
//        gamesRepository.findAll().forEach { log.info(it.toString()) }
//        log.info("\n\n")

    }

}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
