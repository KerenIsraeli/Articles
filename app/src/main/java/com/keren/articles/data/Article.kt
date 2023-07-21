package com.keren.articles.data

import com.google.gson.annotations.SerializedName


data class Article(
    val urlToImage: String,
    @SerializedName(value = "publishedAt") val date: String,
    val author: String
)