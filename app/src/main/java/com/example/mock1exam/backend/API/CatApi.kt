package com.example.assignment4.utils.map

import com.example.mock1exam.backend.API.responses.CatDeleteResponse
import com.example.mock1exam.backend.API.responses.CatResponse
import com.example.mock1exam.backend.API.responses.CatUploadResponse
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface CatApi {
    @GET("v1/breeds?")
    suspend fun getCatsResult(
        @Query("attach_breed") attach_breed: String,
        @Query("limit") limit: Int
    ): List<CatResponse>

    @Headers("x-api-key: 03c41caa-2307-49ae-86ec-c1a57d5ccdeb")
    @Multipart
    @POST("v1/images/upload")
    fun updateCat(
        @Part file: MultipartBody.Part
//        @Part("sub_id") sub_id: String?,
//        @Part("breed_ids") breed_ids: String?
    ): Call<CatUploadResponse>

    @Headers("x-api-key: 03c41caa-2307-49ae-86ec-c1a57d5ccdeb")
    @DELETE("v1/images/{id}")
    fun deleteCatById(
        @Path("id") id: String
    ): Call<CatDeleteResponse>

}