package com.example.mock1exam.backend.API.responses

data class CatUploadResponse(
    val approved: Int,
    val breed_ids: String,
    val height: Int,
    val id: String,
    val original_filename: String,
    val pending: Int,
    val sub_id: String,
    val url: String,
    val width: Int
)