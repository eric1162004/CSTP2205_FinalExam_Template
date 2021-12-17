package com.example.assignment4.utils.map

import android.util.Log
import com.example.mymaptesting.responses.MapResponse
import com.example.mock1exam.backend.Resource
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val mapbox_base_url: String = "https://api.mapbox.com/"
val mapbox_access_token: String =
    "pk.eyJ1IjoiZXJpYzExNjIwMDQiLCJhIjoiY2t2d29heGx1MGtrbjJvdDM0d2hjdnVhdSJ9.fprIOurhSzjFs378lvMBIw"

class MapService {
    private val api: MapApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(mapbox_base_url)
        .build()
        .create(MapApi::class.java)

    suspend fun getLocationResult(
        searchText: String,
        accessToken: String = mapbox_access_token,
        limit: Int = 1
    ): Resource<MapResponse?> {
        val response = try {
            val apiResult = api.getLocationResult(searchText, accessToken, limit)

            Log.d("debug", apiResult.features.isNotEmpty().toString())

            // check if there is any result.
            when {
                apiResult.features.isNotEmpty() -> MapResponse(
                    address = apiResult.features[0].place_name,
                    placeName = apiResult.features[0].text,
                    coordinate = Coordinate(
                        latitude = apiResult.features[0].center[1],
                        longitude = apiResult.features[0].center[0],
                    ),
                    locationImageUrl = "")
                else -> null
            }

        } catch (e: Exception) {
            return Resource.Error("An unknown error occurred.")
        }

        return Resource.Success(response)
    }
}