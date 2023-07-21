package com.keren.articles.data

import android.accounts.NetworkErrorException
import com.keren.articles.customComponents.Resource
import com.keren.articles.domain.ArticlesRepository

class ArticlesRepositoryImpl(
    private val articlesService: IArticlesService
) : ArticlesRepository {

    companion object {
        val TAG = ArticlesRepositoryImpl::class.java.name
    }

    override suspend fun getArticles(): Resource<Articles> {
        val response = articlesService.getArticles()

        if (response.isSuccessful) {
            val articles = Articles(articlesService.getArticles().body()?.articles ?: emptyList())
            return Resource.Success(articles)
        }
        return Resource.NetworkError(response.errorBody().toString())
    }
}