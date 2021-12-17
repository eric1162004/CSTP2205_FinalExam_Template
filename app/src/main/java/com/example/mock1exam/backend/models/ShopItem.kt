package com.example.mock1exam.backend.models

data class ShopItem(
    var id: String = "",
    var name: String = "",
    var price: Float = 0f,
    var count: Int = 0,
    var imageURL: String = "",
    var description: String = "",
    var categories:List<String> = listOf()
)
