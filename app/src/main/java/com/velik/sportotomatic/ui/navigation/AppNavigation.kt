package com.velik.sportotomatic.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.velik.sportotomatic.ui.screens.MatchListScreen
import com.velik.sportotomatic.ui.screens.CouponListScreen

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "match_list") {
        composable("match_list") {
            MatchListScreen(onShowCoupons = {
                navController.navigate("coupon_list")
            })
        }

        composable("coupon_list") {
            CouponListScreen(onBack = {
                navController.popBackStack()
            })
        }
    }
}
