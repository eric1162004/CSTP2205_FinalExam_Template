package com.example.mock1exam.backend.repositories

import android.util.Log
import com.example.mock1exam.backend.Resource
import com.example.mock1exam.backend.models.Breed
import com.example.mock1exam.backend.models.Cat
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow

// TODO: change the name of the repository
class CatRepository {
    private val _collection = FirebaseFirestore.getInstance().collection("Cat")
    private val TAG = "Debug"

    fun getAll(onResponse: (Resource<*>) -> Unit) {
        // For Testing Only - take the top 8 breeds.
        // the whole is too long!!
        _collection
//            .orderBy("timestamp")
            .limit(10)
            .get()
            .addOnSuccessListener { results ->
                var cats = mutableListOf<Cat>()

                results.documents
                    .mapTo(cats) { cat ->
                        cat.toObject<Cat>()!!.apply { id = cat.id }
                    }

                onResponse(Resource.Success(cats))

            }.addOnFailureListener {
                Log.d(TAG, "Error getting documents: ", it)
            }
    }

    fun getById(id: String, onResponse: (Resource<*>) -> Unit) {
        // TODO: change this according your model
        _collection.document(id).get()
            .addOnSuccessListener { cat ->
                var result = cat.toObject<Cat>().apply {
                    this!!.id = cat.id
                }
                onResponse(Resource.Success(result))
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun create(cat: Cat, onResponse: (Resource<*>) -> Unit) {
        val newDocRef = _collection.document()

        // TODO: change this according your model
        newDocRef.set(cat.apply { id = newDocRef.id })
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written  $documentReference")
                onResponse(Resource.Success(documentReference))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding post", e)
                onResponse(Resource.Error("Error adding post", e))
            }
    }

    fun update(cat: Cat, onResponse: (Resource<*>) -> Unit) {
        // TODO: change this according your model
        _collection.document(cat.id)
            .set(cat)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
                onResponse(Resource.Success(cat.id))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating post", e)
                onResponse(Resource.Error("Error updating post", e))
            }
    }

    fun delete(cat: Cat, onResponse: (Resource<*>) -> Unit) {
        // TODO: change this according your model
        _collection.document(cat.id)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")
                onResponse(Resource.Success(cat.id))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error deleting document", e)
                onResponse(Resource.Error("Error deleting post", e))
            }
    }

}