package com.anismhub.ticketsystem.utils

sealed class Resource<out T : Any?> {
    data object None : Resource<Nothing>()
    data object Loading : Resource<Nothing>()
    data class Success<out T : Any>(val data: T) : Resource<T>()
    data class Error(val error: String) : Resource<Nothing>()
}