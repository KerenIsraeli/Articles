package com.keren.articles.data

import retrofit2.Response
import retrofit2.http.*

interface IArticlesService {
    @GET("/v2/everything?q=apple&from=2023-07-19&to=2023-07-19&sortBy=popularity&apiKey=$API_KEY")
    suspend fun getArticles(): Response<Articles>
}

const val ARTICLES_URL = "https://newsapi.org"
const val API_KEY = "67c06ed492be4b9fafd03ec428dabd8e"
