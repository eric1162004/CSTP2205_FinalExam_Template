package com.example.mymaptesting.responses

import com.example.assignment4.utils.map.Coordinate

data class MapResponse(
    var address: String,
    var placeName: String,
    var coordinate: Coordinate,
    var locationImageUrl: String,
)
