package com.b45.outguess.backend.model.jpa

import com.b45.outguess.backend.model.ActionTypes
import javax.persistence.*

@Entity
data class GameMap(
        @OneToMany(fetch = FetchType.EAGER, cascade = [CascadeType.ALL])
        var cells: MutableList<MapCell> = mutableListOf() ,
        @Id @GeneratedValue
        val gameMapId: Long = -1)

@Entity
data class MapCell(
        val x: Int,
        val y: Int,
        val type: ActionTypes,
        @Id @GeneratedValue
        val cellId: Long = -1)