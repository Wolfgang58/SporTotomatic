package com.velik.sportotomatic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.velik.sportotomatic.ui.screens.MatchListScreen
import com.velik.sportotomatic.ui.theme.SportotomaticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportotomaticTheme {
                MatchListScreen()
            }
        }
    }
}
