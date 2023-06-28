package com.gbwa.presentation.auth

import android.util.Log
import androidx.compose.runtime.CompositionContext
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.core.content.ContextCompat
import com.google.firebase.auth.FirebaseAuth
import android.content.Context
import com.gbwa.utils.toast

class AuthViewModel() : ViewModel() {

    var state by mutableStateOf(AuthState())
        private set

    fun login(
        auth: FirebaseAuth,
        email: String,
        password: String,
        handleLoginSuccessful: () -> Unit,
    ) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                state = state.copy(
                    isLoading = true
                )
                if (task.isSuccessful) {
                    state = state.copy(
                        isLoggedIn = true,
                        error = null,
                        isLoading = false
                    )
                    handleLoginSuccessful()
                } else {
                    state = state.copy(
                        isLoggedIn = false,
                        error = task.exception?.message,
                        isLoading = false
                    )
                }
            }

    }

    fun signUp(
        auth: FirebaseAuth,
        email: String,
        password: String,
        handleSignUpSuccessful: () -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener { task ->
                state = state.copy(
                    isLoading = true
                )
                if (task.isSuccessful) {
                    state = state.copy(
                        isLoggedIn = true,
                        error = null,
                        isLoading = false
                    )
                    handleSignUpSuccessful()
                } else {
                    state = state.copy(
                        isLoggedIn = false,
                        error = task.exception?.message,
                        isLoading = false
                    )
                }
            }
    }
}