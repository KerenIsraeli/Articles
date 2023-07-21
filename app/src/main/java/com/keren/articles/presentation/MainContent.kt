package com.keren.articles.presentation

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.*
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.keren.articles.data.Article
import com.keren.articles.presentation.articleData.ArticleData
import com.keren.articles.presentation.articlesViewer.ArticlesViewer

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun BoxScope.MainContent(
    articlesProvider: IArticlesProvider
) {
    val navController = rememberAnimatedNavController()
    val articles = articlesProvider.get().collectAsState()
    val articleToShow: MutableState<Article?> = remember { mutableStateOf(null) }

    AnimatedNavHost(
        navController = navController,
        startDestination = ScreenType.MainViewer.route
    ) {
        routeComposable(ScreenType.MainViewer) {
            ArticlesViewer(articlesResponse = articles.value) {
                articleToShow.value = it
                navController.navigate(ScreenType.ArticleData.route)
            }
        }
        routeComposable(ScreenType.ArticleData) {
            val article = articleToShow.value

            if (article == null) {
                navController.navigateUp()
            } else {
                ArticleData(
                    article,
                    onBackPress = {
                        navController.navigateUp()
                    }
                )
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.routeComposable(
    type: ScreenType,
    content: @Composable() (AnimatedVisibilityScope.(NavBackStackEntry) -> Unit)
) = composable(
    route = type.route,
    enterTransition = { slideInHorizontally(initialOffsetX = { it / 2 }, animationSpec = tween(200)) },
    exitTransition = { slideOutHorizontally(targetOffsetX = { it / 2 }, animationSpec = tween(0)) },
    popEnterTransition = { slideInHorizontally(animationSpec = tween(200)) },
    popExitTransition = { slideOutHorizontally(animationSpec = tween(0)) },
    content = content
)

enum class ScreenType(val route: String) {
    MainViewer("MainViewer"),
    ArticleData("ArticleData")
}


