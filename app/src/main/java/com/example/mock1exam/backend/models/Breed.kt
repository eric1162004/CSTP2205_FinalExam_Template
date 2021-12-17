package com.example.mock1exam.backend.models

// TODO: change this according your model
// TODO: always include a <id> field in your model
data class Breed(
    var uid: String = "",
    var id: Int = 0,
    var breed: String = "",
    var description: String = "",
    var facts: List<String> = listOf(),
)

