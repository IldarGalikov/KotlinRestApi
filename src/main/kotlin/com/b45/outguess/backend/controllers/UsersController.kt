package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.services.UsersService

import org.springframework.web.bind.annotation.*



@RestController
class UsersController(val usersService: UsersService) {

    @GetMapping("/users")
    fun getUsers() : List<UserResponse> = usersService.getAll().map { u -> UserResponse(u.id, u.username) }

}

data class UserResponse(val id: Long, val name: String)

