package com.b45.outguess.backend.model.jpa

import com.b45.outguess.backend.model.ActionTypes
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class InventoryItem(
        val itemType: ActionTypes,
        @Id @GeneratedValue
        val inventoryItemId: Long = -1
)