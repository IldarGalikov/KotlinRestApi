package com.ildar.outguess.backend.model

class Game(val users: List<User>) {

    var round: Int = 1
    val players: List<Player> = users.map {u -> Player(u,this) }
    val fieldSize: Int = 2

    init{
        players.forEach{
            p -> p.generateMap()
        }
    }
}