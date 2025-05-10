package com.velik.sportotomatic

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import com.velik.sportotomatic.ui.screens.CouponListScreen
import com.velik.sportotomatic.ui.screens.MatchListScreen
import com.velik.sportotomatic.ui.theme.SportotomaticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SportotomaticTheme {
                AppNavigator()
            }
        }
    }
}

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "matchList") {
        composable("matchList") {
            MatchListScreen(navController)
        }
        composable("couponList") {
            CouponListScreen(onBack = { navController.popBackStack() })
        }
    }
}
