package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.repositories.UsersRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UsersController(private val usersRepository: UsersRepository) {

    @GetMapping("/users")
    fun findAll() = usersRepository.findAll()

    @GetMapping("/users/{lastName}")
    fun findByLastName(@PathVariable email:String)
            = usersRepository.findByEmail(email)


}