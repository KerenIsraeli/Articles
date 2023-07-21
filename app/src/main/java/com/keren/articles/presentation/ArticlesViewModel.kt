package com.keren.articles.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keren.articles.customComponents.Resource
import com.keren.articles.data.Article
import com.keren.articles.data.Articles
import com.keren.articles.domain.ArticlesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticlesViewModel(
    private val articlesRepository: ArticlesRepository
) : ViewModel(), IArticlesProvider {

    private val _articleState: MutableStateFlow<Resource<Articles>> = MutableStateFlow(
        Resource.NetworkError("Not yet initialized")
    )

    init {
        viewModelScope.launch {
            _articleState.value = articlesRepository.getArticles()
        }
    }

    override fun get(): StateFlow<Resource<Articles>> = _articleState

}