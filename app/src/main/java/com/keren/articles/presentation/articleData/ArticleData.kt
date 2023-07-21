package com.keren.articles.presentation.articleData

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.keren.articles.R
import com.keren.articles.data.Article

@Composable
fun ArticleData(
    article: Article,
    onBackPress: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Cyan)) {
            IconButton(
                modifier = Modifier.padding(24.dp),
                onClick = onBackPress
            ) {
                Icon(
                    painter = painterResource(R.drawable.baseline_arrow_back_24),
                    contentDescription = stringResource(R.string.back)
                )
            }

        Column(Modifier.weight(1f), horizontalAlignment = CenterHorizontally) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Image(
                    painter = rememberAsyncImagePainter(article.urlToImage),
                    contentDescription = null,
                    modifier = Modifier.size(400.dp)
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
            } }
        }
    }
}

// TODO preview