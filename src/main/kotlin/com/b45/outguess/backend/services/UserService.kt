package com.b45.outguess.backend.services

import com.b45.outguess.backend.model.jpa.User
import com.b45.outguess.backend.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class UserService(val usersRepository: UsersRepository) {
    fun getAll(): List<User> = usersRepository.findAll()
    fun getUserById(id: Long) = usersRepository.findById(id)

}
