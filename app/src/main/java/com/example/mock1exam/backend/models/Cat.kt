package com.example.mock1exam.backend.models

import com.google.firebase.Timestamp

// TODO: change this according your model
// TODO: always include a <id> field in your model
data class Cat(
    var id: String = "",
    var name: String = "",
    var sex: String = "",
    var weight: Float = 0f,
    var location: Location = Location(),
    var imageURLs: MutableList<String> = mutableListOf(),
    var description: String = "",
    var breeds:List<String> = listOf(),
    var age: Int = 0,
    var timestamp: Timestamp? = Timestamp.now(),
)

