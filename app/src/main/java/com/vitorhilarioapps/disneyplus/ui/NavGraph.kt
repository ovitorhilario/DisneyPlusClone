package com.vitorhilarioapps.disneyplus.ui

import androidx.annotation.StringRes
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.geometry.Offset
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navOptions
import androidx.navigation.navigation
import com.vitorhilarioapps.disneyplus.R
import com.vitorhilarioapps.disneyplus.ui.destinations.home.HomeScreen
import com.vitorhilarioapps.disneyplus.ui.destinations.login.LoginScreen
import com.vitorhilarioapps.disneyplus.ui.destinations.poster.PosterScreen
import com.vitorhilarioapps.disneyplus.ui.destinations.splash.SplashScreen
import org.koin.androidx.compose.koinViewModel

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object SPLASH: Screen("splash", R.string.splash)
    object LOGIN: Screen("login", R.string.login)
    object HOME: Screen("home/", R.string.home)
    object POSTER: Screen("poster/", R.string.home)
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(
        navController = navController,
        startDestination = Screen.SPLASH.route,
        enterTransition = {

            val initialRoute = initialState.destination.route

            if (initialRoute?.startsWith(Screen.LOGIN.route) == true) {
                slideInHorizontally(tween(500)) { it }
            } else if (initialRoute?.startsWith(Screen.HOME.route) == true) {
                slideInVertically(tween(1000)) { it }
            } else {
                fadeIn()
            }

        },
        exitTransition = {
            fadeOut()
        }
    ) {
        composable(route = Screen.SPLASH.route) {
            SplashScreen(
                onSplashTimeout = {
                    navController.navigate(Screen.LOGIN.route) {
                        popUpTo(Screen.SPLASH.route) {
                            inclusive = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.LOGIN.route) {
            LoginScreen(
                onLoginToHome = { profileId ->
                    navController.navigate(Screen.HOME.route + profileId)
                }
            )
        }

        composable(route = Screen.HOME.route + "{profileId}",
            arguments = listOf(navArgument("profileId") { type = NavType.IntType })
        ) { backStackEntry ->
            val profileId = backStackEntry.arguments?.getInt("profileId") ?: 0
            HomeScreen(
                viewModel = koinViewModel(),
                profileId = profileId,
                openPoster = { movieId ->
                    navController.navigate(Screen.POSTER.route + movieId) {
                        popUpTo(Screen.HOME.route) {
                            saveState = true
                        }
                    }
                }
            )
        }

        composable(route = Screen.POSTER.route + "{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backStackEntry ->
            val movieId = backStackEntry.arguments?.getInt("movieId") ?: 0
            PosterScreen(koinViewModel(), movieId, onClose = { navController.navigateUp() })
        }
    }
}