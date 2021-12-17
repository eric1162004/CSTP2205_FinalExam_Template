package com.example.mock1exam.backend.repositories

import android.util.Log
import com.example.mock1exam.backend.Resource
import com.example.mock1exam.backend.models.ShopItem
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject

class ShopItemRepository {
    private val _collection = FirebaseFirestore.getInstance().collection("ShopItem")
    private val TAG = "Debug"

    fun getAll(onResponse: (Resource<*>) -> Unit) {
        // For Testing Only - take the top 8 breeds.
        // the whole is too long!!
        _collection
//            .orderBy("timestamp")
            .limit(10)
            .get()
            .addOnSuccessListener { results ->
                var shopItems = mutableListOf<ShopItem>()

                results.documents
                    .mapTo(shopItems) { shopItem ->
                        shopItem.toObject<ShopItem>()!!.apply { id = shopItem.id }
                    }

                onResponse(Resource.Success(shopItems))

            }.addOnFailureListener {
                Log.d(TAG, "Error getting documents: ", it)
            }
    }

    fun getById(id: String, onResponse: (Resource<*>) -> Unit) {
        // TODO: change this according your model
        _collection.document(id).get()
            .addOnSuccessListener { shopItem ->
                var result = shopItem.toObject<ShopItem>().apply {
                    this!!.id = shopItem.id
                }
                onResponse(Resource.Success(result))
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
    }

    fun create(shopItem: ShopItem, onResponse: (Resource<*>) -> Unit) {
        val newDocRef = _collection.document()

        // TODO: change this according your model
        newDocRef.set(shopItem.apply { id = newDocRef.id })
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written  $documentReference")
                onResponse(Resource.Success(documentReference))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding post", e)
                onResponse(Resource.Error("Error adding post", e))
            }
    }

    fun update(shopItem: ShopItem, onResponse: (Resource<*>) -> Unit) {
        // TODO: change this according your model
        _collection.document(shopItem.id)
            .set(shopItem)
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
                onResponse(Resource.Success(shopItem.id))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error updating post", e)
                onResponse(Resource.Error("Error updating post", e))
            }
    }

    fun delete(shopItem: ShopItem, onResponse: (Resource<*>) -> Unit) {
        // TODO: change this according your model
        _collection.document(shopItem.id)
            .delete()
            .addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully deleted!")
                onResponse(Resource.Success(shopItem.id))
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error deleting document", e)
                onResponse(Resource.Error("Error deleting post", e))
            }
    }

}