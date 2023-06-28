package com.gbwa.presentation.auth

data class AuthState(
    val isLoggedIn: Boolean = false,
    val error: String? = null,
    val isLoading: Boolean = false

)
