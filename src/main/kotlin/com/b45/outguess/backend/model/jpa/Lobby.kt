package com.b45.outguess.backend.model.jpa

import javax.persistence.*

@Entity
data class GameLobby(@Id
                     @GeneratedValue(strategy = GenerationType.IDENTITY)
                     val id: Long = 0,
                     @OneToMany  var users: MutableList<User> = ArrayList(),
                     val name: String)
