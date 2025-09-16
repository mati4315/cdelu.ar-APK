package com.example.cdeluapk.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String) {
    object Feed : Screen("feed")
    object Login : Screen("login")
    object Register : Screen("register")
    object NewsDetail : Screen("news/{newsId}") {
        val arguments = listOf(
            navArgument("newsId") { type = NavType.IntType }
        )
    }
    object Lottery : Screen("lottery")
    object LotteryDetail : Screen("lottery/{lotteryId}") {
        val arguments = listOf(
            navArgument("lotteryId") { type = NavType.IntType }
        )
    }
    object Survey : Screen("survey")
    object Profile : Screen("profile")
}
