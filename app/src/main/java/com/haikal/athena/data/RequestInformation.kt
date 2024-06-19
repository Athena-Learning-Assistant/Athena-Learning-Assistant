package com.haikal.athena.data

sealed class RequestInformation<out R> private constructor() {
    data class Success<out T>(val data: T) : RequestInformation<T>()
    data class Error(val error: String) : RequestInformation<Nothing>()
    data object Loading : RequestInformation<Nothing>()
}