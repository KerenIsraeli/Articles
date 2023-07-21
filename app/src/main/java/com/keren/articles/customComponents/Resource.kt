package com.keren.articles.customComponents

sealed class Resource<out T> {
    data class Success<out T>(val data: T? = null) : Resource<T>()
    class NetworkError<out T>(val error: String) : Resource<T>()
    class Loading<out T> : Resource<T>()
}