package com.keren.articles.data

import com.google.gson.annotations.SerializedName

data class Articles(
    @SerializedName(value = "articles") val articles: List<Article>
) {
}