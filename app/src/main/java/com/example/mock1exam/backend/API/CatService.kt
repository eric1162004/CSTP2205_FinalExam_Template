package com.example.mock1exam.backend.API

import android.util.Log
import com.example.assignment4.utils.map.CatApi
import com.example.mock1exam.backend.API.responses.CatResponse
import com.example.mock1exam.backend.Resource
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import okhttp3.MultipartBody
import okhttp3.RequestBody

private val api_endpoint: String = "https://api.thecatapi.com/"

class CatService {
    private val api: CatApi = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(api_endpoint)
        .build()
        .create(CatApi::class.java)

    suspend fun getCatsByBreed(
        breed: String = "",
        limit: Int = 1
    ): Resource<List<CatResponse>> {
        val response = try {
            api.getCatsResult(breed, limit)
        } catch (e: Exception) {
            return Resource.Error(e.message!!)
        }
        return Resource.Success(response)
    }

    suspend fun deleteCatById(id: String): Resource<*> {
        val response = try {
            api.deleteCatById(id).execute()
        } catch (e: Exception) {
            Log.d("debug", e.message!!)
            return Resource.Error<String>(e.message!!)
        }
        return Resource.Success(response)
    }

    suspend fun uploadCat(
        file: File,
        sub_id: String = "123",
        breed_ids: String = "abys",
    ): Resource<*> {
        val requestFile = RequestBody.create("image/jpeg".toMediaTypeOrNull(), file);
        val body = MultipartBody.Part.createFormData("file", file.name, requestFile)

        val response =
//            try {
            api.updateCat(
                body,
//                file,
//                sub_id,
//                breed_ids
            ).execute()

//        } catch (e: Exception) {
//            Log.d("debug", e.message!!)
//            return Resource.Error<String>(e.message!!)
//        }

        return Resource.Success(response)
    }
}