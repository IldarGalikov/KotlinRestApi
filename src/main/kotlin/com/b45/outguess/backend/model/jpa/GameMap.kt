package com.b45.outguess.backend.model.jpa

import org.hibernate.annotations.Cascade
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.OneToMany

@Entity
data class GameMap(@Id @GeneratedValue
                   val id: Long = -1,
                   val size: Int = 0,
                   @OneToMany
                   var cells: List<MapCell> = emptyList())

@Entity
data class MapCell(@Id @GeneratedValue
                   val id: Long = -1,
                   val x: Int,
                   val y: Int,
                   val type: String)