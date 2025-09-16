package com.example.cdeluapk.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cdeluapk.ui.screens.auth.LoginScreen
import com.example.cdeluapk.ui.screens.auth.RegisterScreen
import com.example.cdeluapk.ui.screens.feed.FeedScreen
import com.example.cdeluapk.ui.screens.lottery.LotteryDetailScreen
import com.example.cdeluapk.ui.screens.lottery.LotteryScreen
import com.example.cdeluapk.ui.screens.news.NewsDetailScreen
import com.example.cdeluapk.ui.screens.profile.ProfileScreen
import com.example.cdeluapk.ui.screens.survey.SurveyScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Feed.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Feed principal
        composable(Screen.Feed.route) {
            FeedScreen(navController = navController)
        }
        
        // Autenticación
        composable(Screen.Login.route) {
            LoginScreen(navController = navController)
        }
        
        composable(Screen.Register.route) {
            RegisterScreen(navController = navController)
        }
        
        // Noticias
        composable(
            route = Screen.NewsDetail.route,
            arguments = Screen.NewsDetail.arguments
        ) { backStackEntry ->
            val newsId = backStackEntry.arguments?.getInt("newsId") ?: 0
            NewsDetailScreen(
                newsId = newsId,
                navController = navController
            )
        }
        
        // Loterías
        composable(Screen.Lottery.route) {
            LotteryScreen(navController = navController)
        }
        
        composable(
            route = Screen.LotteryDetail.route,
            arguments = Screen.LotteryDetail.arguments
        ) { backStackEntry ->
            val lotteryId = backStackEntry.arguments?.getInt("lotteryId") ?: 0
            LotteryDetailScreen(
                lotteryId = lotteryId,
                navController = navController
            )
        }
        
        // Encuestas
        composable(Screen.Survey.route) {
            SurveyScreen(navController = navController)
        }
        
        // Perfil
        composable(Screen.Profile.route) {
            ProfileScreen(navController = navController)
        }
    }
}
