package com.example.mock1exam.backend.repositories;

import android.util.Log
import com.example.mock1exam.backend.Resource
import com.example.mock1exam.backend.models.Breed
import com.example.mock1exam.backend.models.Cat
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ktx.toObject

class BreedRepository {
    private val _collection = FirebaseFirestore.getInstance().collection("Breed")
    private val TAG = "Debug"

    fun getAll(onResponse: (Resource<*>) -> Unit) {
        _collection.get()
            .addOnSuccessListener { results ->
                var breeds = mutableListOf<Breed>()

                // Only take the top 8 breeds.
                // the whole is too long!!
                results.documents
                    .take(8)
                    .mapTo(breeds) { breed ->
                        breed.toObject<Breed>()!!.apply { uid = breed.id }
                    }

                onResponse(Resource.Success(breeds))

            }.addOnFailureListener {
                Log.d(TAG, "Error getting documents: ", it)
            }
    }
}
