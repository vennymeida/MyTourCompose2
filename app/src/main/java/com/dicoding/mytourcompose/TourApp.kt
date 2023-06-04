package com.dicoding.mytourcompose

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.mytourcompose.ui.navigation.NavigationItem
import com.dicoding.mytourcompose.ui.theme.MyTourComposeTheme
import com.dicoding.mytourcompose.ui.navigation.Screen
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.dicoding.mytourcompose.ui.HomeScreen
import com.dicoding.mytourcompose.ui.about.AboutScreen
import androidx.compose.runtime.getValue
import androidx.navigation.compose.currentBackStackEntryAsState
import com.dicoding.mytourcompose.ui.detail.DetailScreen

@Composable
fun TourApp(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        bottomBar = {
            BottomBar(navController)
        },
        modifier = modifier
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
            composable(Screen.About.route) {
                AboutScreen()
            }
            composable(Screen.DetailTour.route) { backStackEntry ->
                val tourId = backStackEntry.arguments?.getString("tourId")?.toIntOrNull()
                if (tourId != null) {
                    DetailScreen(tourId = tourId, navController = navController)
                } else {
                    // handle error or navigate to a default screen
                }
            }
        }
    }
}

@Composable
private fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(R.string.menu_home),
                icon = Icons.Default.Home,
                screen = Screen.Home
            ),
            NavigationItem(
                title = stringResource(R.string.menu_about),
                icon = Icons.Default.AccountCircle,
                screen = Screen.About
            ),
        )
        BottomNavigation {
            navigationItems.map { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title
                        )
                    },
                    label = { Text(item.title) },
                    selected = currentRoute == item.screen.route,
                    onClick = {
                        navController.navigate(item.screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            restoreState = true
                            launchSingleTop = true
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TourAppPreview() {
    MyTourComposeTheme {
        val navController = rememberNavController()
        TourApp(navController = navController)
    }
}
