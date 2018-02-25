package com.b45.outguess.backend.controllers

import com.b45.outguess.backend.services.UserService

import org.springframework.web.bind.annotation.*



@RestController
class UsersController(val userService: UserService) {

    @GetMapping("/users")
    fun getUsers() : List<UserResponse> = userService.getAll().map { u -> UserResponse(u.id, u.username) }

}

data class UserResponse(val id: Long, val name: String)

