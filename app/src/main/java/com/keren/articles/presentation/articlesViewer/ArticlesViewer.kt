package com.keren.articles.presentation.articlesViewer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.keren.articles.customComponents.Resource
import com.keren.articles.data.Article
import com.keren.articles.data.Articles

@Composable
fun BoxScope.ArticlesViewer(
    modifier: Modifier = Modifier,
    articlesResponse: Resource<Articles>,
    onArticleClicked: (Article) -> Unit
) {
    when (articlesResponse) {
        is Resource.Loading -> {} // TODO show loader
        is Resource.NetworkError -> {
            // TODO show error
            Text(
                modifier = Modifier.align(Center),
                text = articlesResponse.error,
                fontSize = 32.sp
            )
        }
        is Resource.Success -> {
            val articles = articlesResponse.data?.articles ?: emptyList()

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(
                    start = 12.dp,
                    top = 44.dp,
                    end = 12.dp
                ),
                modifier = modifier.then(Modifier.fillMaxSize()).clip(RectangleShape),
                content = {
                    items(articles.size) { index ->
                        val article = articles[index]
                        ArticleItem(article = article) {
                            onArticleClicked(article)
                        }
                    }
                }
            )
        }
    }
}

@Composable
private fun ArticleItem(
    modifier: Modifier = Modifier,
    article: Article,
    onArticleClicked: () -> Unit
) {
    Button(
        onClick = onArticleClicked,
        modifier = modifier.padding(8.dp),
        shape = RoundedCornerShape(12.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Image(
                painter = rememberAsyncImagePainter(article.urlToImage),
                contentDescription = null,
                modifier = Modifier.size(128.dp)
            )
            Text(
                text = article.date,
                fontSize = 16.sp,
                fontWeight = FontWeight.W700
            )
            Text(
                text = article.author,
                fontSize = 14.sp,
                fontWeight = FontWeight.W500
            )
        }
    }
}