package com.keren.articles.presentation

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.*
import androidx.compose.material3.Typography
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.keren.articles.customComponents.LightColors
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val viewModel: ArticlesViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(
            ComposeView(this).apply {
                setContent {
                    val darkTheme: Boolean = false

                    val colorScheme = when {
                        darkTheme -> darkColorScheme(
                            primary = Red,
                            secondary = Green,
                            tertiary = Green
                        )
                        else -> lightColorScheme(
                            primary = Blue,
                            secondary = Green,
                            tertiary = White,
                            background = Color.White,
                            surface = Color.White,
                            onSurface = Color.White,
                            onBackground = Color.White,
                            inverseOnSurface = Color.White,
                            tertiaryContainer = Color.White,
                            onTertiary = Color.White,
                            onErrorContainer = Color.White,
                            onTertiaryContainer = Color.White,
                            scrim = Color.White
                        )
                    }
                    val view = LocalView.current
                    if (!view.isInEditMode) {
                        SideEffect {
                            val window = (view.context as Activity).window
                            window.statusBarColor = colorScheme.primary.toArgb()
                            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
                        }
                    }

                    MaterialTheme(
                        colorScheme = colorScheme
                    ) {
                        Box(modifier = Modifier.fillMaxSize().background(MaterialTheme.colorScheme.background)) {
                            MainContent(viewModel)

                        }
                    }
                }
            }
        )
    }
}