package com.keren.articles.domain

import com.keren.articles.customComponents.Resource
import com.keren.articles.data.Articles

interface ArticlesRepository {

    suspend fun getArticles(): Resource<Articles>
}