package com.example.mock1exam.utils.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Auth() {
    private var _auth: FirebaseAuth = Firebase.auth
    var currentUser: FirebaseUser? = null

    init {
        // Initialize Firebase Auth
        currentUser = _auth.currentUser
    }

    fun signUpWithEmailAndPassword(
        email: String,
        password: String,
        onError: (errorMessage: String?) -> Unit = {},
        onSuccess: () -> Unit
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            _auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        currentUser = _auth.currentUser

                        onSuccess()
                    }
                }.addOnFailureListener {
                    onError(it.message)
                }
        }
    }

    fun signInWithEmailAndPassword(
        email: String,
        password: String,
        onError: (errorMessage: String?) -> Unit = {},
        onSuccess: () -> Unit
    ) {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            _auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI
                        currentUser = _auth.currentUser

                        onSuccess()
                    }
                }.addOnFailureListener {
                        onError(it.message)
                }
        }
    }

    fun signOut(onSuccess: () -> Unit) {
        _auth.signOut()
        currentUser = null
        onSuccess()
    }
}


