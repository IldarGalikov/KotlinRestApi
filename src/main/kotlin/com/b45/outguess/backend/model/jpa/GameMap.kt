package com.b45.outguess.backend.model.jpa

import javax.persistence.*

@Entity
data class GameMap(@Id @GeneratedValue
                   val id: Long = -1,
                   val size: Int = 0,
                   @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
                   var cells: List<MapCell> = emptyList())

@Entity
data class MapCell(@Id @GeneratedValue
                   val id: Long = -1,
                   val x: Int,
                   val y: Int,
                   val type: String)