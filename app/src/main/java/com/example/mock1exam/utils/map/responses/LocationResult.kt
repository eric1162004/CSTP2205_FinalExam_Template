package com.example.mymaptesting.responses

data class LocationResult(
    val attribution: String,
    val features: List<Feature>,
    val query: List<String>,
    val type: String
)

