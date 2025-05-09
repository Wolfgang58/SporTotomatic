package com.velik.sportotomatic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.velik.sportotomatic.ui.navigation.AppNavigation
import com.velik.sportotomatic.ui.theme.SportotomaticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportotomaticTheme {
                val navController = rememberNavController()
                AppNavigation(navController)
            }
        }
    }
}
