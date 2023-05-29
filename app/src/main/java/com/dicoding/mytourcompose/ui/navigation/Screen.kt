package com.dicoding.mytourcompose.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object DetailTour : Screen("home/{tourId}") {
        fun createRoute(tourId: Int) = "home/$tourId"
    }
}