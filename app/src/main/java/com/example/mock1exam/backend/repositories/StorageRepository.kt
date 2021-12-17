/**
 * @ Author: 2205 Team (Food Village)
 * @ Create Time: 2021-11-11 20:41:02
 * @ Description: Contains FireStorageRepo class for storing User's and Posts' images in FireStorage
 */

package com.example.foodvillage2205.model.repositories

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.google.android.gms.tasks.Task
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

class StorageRepository() {
    private val imageRef = Firebase.storage.reference

    fun uploadImage(
        fileUri: Uri,
        onImageUploaded: (Uri, String) -> Unit
    ) = CoroutineScope(Dispatchers.IO).launch {
        try {
            fileUri?.let {
                // Create a timestamp
                val s = SimpleDateFormat("ddMMyyyyhhmmss")
                val timestampStr: String = s.format(Date())
                val formattedFilename = "${fileUri.lastPathSegment}-$timestampStr"

                // Upload the image
                imageRef.child("images/$formattedFilename")
                    .putFile(it)
                    .await()

                // Retrieve the Download Url and return it with the onImageUploaded callback
                val imageUrl = imageRef
                    .child("images/$formattedFilename")
                    .downloadUrl
                    .await()

                onImageUploaded(imageUrl, formattedFilename)
            }
        } catch (e: Exception) {
            Log.d("debug", e.message!!)
        }
    }

    fun deleteImage(filename: String) =
        CoroutineScope(Dispatchers.IO).launch {
            try {
                imageRef.child("images/$filename").delete().await()
            } catch (e: Exception) {
                Log.d("debug", e.message!!)
            }
        }

    fun getImageDownloadUrl(filename: String): Task<Uri> {
        return imageRef.child("images/$filename").downloadUrl
    }
}