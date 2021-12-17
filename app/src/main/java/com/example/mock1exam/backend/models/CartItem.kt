package com.example.mock1exam.backend.models

data class CartItem(
    var id: String = "",
    var shopItem: ShopItem = ShopItem(),
    var wantedQuantity: Int = 0
)
