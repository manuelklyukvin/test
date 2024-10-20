package com.manuelklyukvin.test.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.manuelklyukvin.code.presentation.screen.CodeScreen
import com.manuelklyukvin.core.presentation.navigation.graphs.AppNavGraph
import com.manuelklyukvin.core.presentation.theme.AppTheme
import com.manuelklyukvin.feed.presentation.screen.FeedScreen
import com.manuelklyukvin.sign_in.presentation.screen.SignInScreen

@Composable
fun AppScreen() {
    AppTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = AppTheme.colorScheme.black
        ) {
            AppNavGraph(
                signInScreen = {
                    SignInScreen()
                },
                codeScreen = { email ->
                    CodeScreen(email = email)
                },
                feedScreen = {
                    FeedScreen()
                }
            )
        }
    }
}