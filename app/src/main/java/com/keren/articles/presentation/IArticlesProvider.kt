package com.keren.articles.presentation

import com.keren.articles.customComponents.Resource
import com.keren.articles.data.Article
import com.keren.articles.data.Articles
import kotlinx.coroutines.flow.StateFlow

interface IArticlesProvider {

    fun get(): StateFlow<Resource<Articles>>

}