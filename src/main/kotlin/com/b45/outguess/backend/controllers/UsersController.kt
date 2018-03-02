package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.services.UsersService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class UsersController(val usersService: UsersService) {

    @GetMapping("/users")
    fun getUsers(): List<User> = usersService.getAll()

}

