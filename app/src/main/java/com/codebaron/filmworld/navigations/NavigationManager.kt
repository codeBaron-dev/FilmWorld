package com.codebaron.filmworld.navigations

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codebaron.filmworld.MainActivity
import com.codebaron.filmworld.screens.LandingScreen
import com.codebaron.filmworld.screens.accounts.AccountOption
import com.codebaron.filmworld.screens.accounts.AddProfile
import com.codebaron.filmworld.screens.downloads.DownloadsPage
import com.codebaron.filmworld.screens.games.GamesPage
import com.codebaron.filmworld.screens.home.HomeActivity
import com.codebaron.filmworld.screens.home.HomePage
import com.codebaron.filmworld.screens.hot.NewHotPage
import com.codebaron.filmworld.screens.shorts.ScenesPage

@Composable
fun NavigationManager(
    mainActivity: MainActivity,
    networkState: Boolean
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        route = Graphs.ROOT_GRAPH.name,
        startDestination = Destinations.SPLASH_SCREEN.name
    ) {
        composable(Destinations.SPLASH_SCREEN.name) {
            LandingScreen(networkState = networkState, navigationController = navController)
        }
        composable(Destinations.ACCOUNT_SELECTION_SCREEN.name) {
            AccountOption(
                mainActivity = mainActivity,
                networkState = networkState,
                navHostController = navController
            )
        }
        composable(Destinations.ADD_PROFILE_SCREEN.name) {
            AddProfile(
                mainActivity = mainActivity,
                networkState = networkState,
                navHostController = navController
            )
        }
    }
}

@Composable
fun HomePageNavigationManager(
    homeActivity: HomeActivity,
    networkState: Boolean
) {
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = BottomSheetNavigationManager.Home.route
    ) {
        composable(route = BottomSheetNavigationManager.Home.route) {
            HomePage(
                mainActivity = homeActivity,
                networkState = networkState,
                navHostController = navHostController
            )
        }
        composable(route = BottomSheetNavigationManager.Games.route) {
            GamesPage(
                mainActivity = homeActivity,
                networkState = networkState,
                navHostController = navHostController
            )
        }
        composable(route = BottomSheetNavigationManager.NewHot.route) {
            NewHotPage(
                mainActivity = homeActivity,
                networkState = networkState,
                navHostController = navHostController
            )
        }
        composable(route = BottomSheetNavigationManager.Scenes.route) {
            ScenesPage(
                mainActivity = homeActivity,
                networkState = networkState,
                navHostController = navHostController
            )
        }
        composable(route = BottomSheetNavigationManager.Downloads.route) {
            DownloadsPage(
                mainActivity = homeActivity,
                networkState = networkState,
                navHostController = navHostController
            )
        }
    }
}