package com.codebaron.filmworld.navigations

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.codebaron.filmworld.screens.downloads.DownloadsPage
import com.codebaron.filmworld.screens.games.GamesPage
import com.codebaron.filmworld.screens.home.HomeActivity
import com.codebaron.filmworld.screens.home.HomePage
import com.codebaron.filmworld.screens.hot.NewHotPage
import com.codebaron.filmworld.screens.shorts.ScenesPage
import com.codebaron.filmworld.shared.HeaderBar

sealed class BottomSheetNavigationManager(
    val route: String,
    val title: String,
    val icon: ImageVector
) {

    object Home : BottomSheetNavigationManager(
        route = BottomSheetDestinations.HOME_SCREEN,
        title = BottomSheetDestinations.HOME_SCREEN,
        icon = Icons.Default.Home
    )

    object Games : BottomSheetNavigationManager(
        route = BottomSheetDestinations.GAMES_SCREEN,
        title = BottomSheetDestinations.GAMES_SCREEN,
        icon = Icons.Default.VideogameAsset
    )

    object NewHot : BottomSheetNavigationManager(
        route = BottomSheetDestinations.NEW_HOT_SCREEN,
        title = BottomSheetDestinations.NEW_HOT_SCREEN,
        icon = Icons.Default.VideoLibrary
    )

    object Scenes : BottomSheetNavigationManager(
        route = BottomSheetDestinations.SCENES_SCREEN,
        title = BottomSheetDestinations.SCENES_SCREEN,
        icon = Icons.Default.Coffee
    )

    object Downloads : BottomSheetNavigationManager(
        route = BottomSheetDestinations.DOWNLOADS_SCREEN,
        title = BottomSheetDestinations.DOWNLOADS_SCREEN,
        icon = Icons.Default.Save
    )
}

object BottomSheetDestinations {
    const val HOME_SCREEN = "Home"
    const val GAMES_SCREEN = "Games"
    const val NEW_HOT_SCREEN = "New&Hot"
    const val SCENES_SCREEN = "Scenes"
    const val DOWNLOADS_SCREEN = "Downloads"
}

@Composable
fun BottomNav(homeActivity: HomeActivity, networkState: Boolean) {
    val navController = rememberNavController()
    Scaffold(
        topBar = { HeaderBar() },
        bottomBar = {
            BottomNavigation(
                backgroundColor = androidx.compose.ui.graphics.Color.Black,
            ) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination
                val screens = listOf(
                    BottomSheetNavigationManager.Home,
                    BottomSheetNavigationManager.Games,
                    BottomSheetNavigationManager.NewHot,
                    BottomSheetNavigationManager.Scenes,
                    BottomSheetNavigationManager.Downloads,
                )
                screens.forEach { screen ->
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                // Pop up to the start destination of the graph to
                                // avoid building up a large stack of destinations
                                // on the back stack as users select items
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                // Avoid multiple copies of the same destination when
                                // reselecting the same item
                                launchSingleTop = true
                                // Restore state when reselecting a previously selected item
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController,
            startDestination = BottomSheetNavigationManager.Home.route,
            Modifier.padding(innerPadding)
        ) {

            composable(route = BottomSheetNavigationManager.Home.route) {
                HomePage(
                    mainActivity = homeActivity,
                    networkState = networkState,
                    navHostController = navController
                )
            }
            composable(route = BottomSheetNavigationManager.Games.route) {
                GamesPage(
                    mainActivity = homeActivity,
                    networkState = networkState,
                    navHostController = navController
                )
            }
            composable(route = BottomSheetNavigationManager.NewHot.route) {
                NewHotPage(
                    mainActivity = homeActivity,
                    networkState = networkState,
                    navHostController = navController
                )
            }
            composable(route = BottomSheetNavigationManager.Scenes.route) {
                ScenesPage(
                    mainActivity = homeActivity,
                    networkState = networkState,
                    navHostController = navController
                )
            }
            composable(route = BottomSheetNavigationManager.Downloads.route) {
                DownloadsPage(
                    mainActivity = homeActivity,
                    networkState = networkState,
                    navHostController = navController
                )
            }
        }
    }
}