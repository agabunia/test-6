package com.example.test_6.common

sealed class Resource<T>(
    val success: String? = null,
    val error: String? = null,
    val loading: Boolean = false
) {
    data class Success<T>(val successMessage: String) : Resource<T>(success = successMessage)
    data class Fail<T>(val errorMessage: String) : Resource<T>(error = errorMessage)
    data class Loading<T>(val isLoading: Boolean) : Resource<T>(loading = isLoading)
}
