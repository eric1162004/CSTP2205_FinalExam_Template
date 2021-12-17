/**
 * @ Author: 2205 Team (Food Village)
 * @ Create Time: 2021-11-11 20:41:02
 * @ Description: File holds User data class
 */

package com.example.foodvillage2205.model.entities

import com.example.mock1exam.backend.models.CartItem
import com.google.firebase.Timestamp

data class User(
    var id: String = "",
    var name: String = "",
    var email: String = "",
    var thumbnailUrl: String = "",
    var cartItems: MutableList<CartItem> = mutableListOf(),
    var timestamp: Timestamp? = Timestamp.now()
)
