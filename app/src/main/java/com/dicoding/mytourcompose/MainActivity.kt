package com.dicoding.mytourcompose

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.mytourcompose.model.Tour
import com.dicoding.mytourcompose.ui.HomeScreen
import com.dicoding.mytourcompose.ui.detail.DetailScreen
import com.dicoding.mytourcompose.ui.theme.MyTourComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTourComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController, startDestination = "home") {
                        composable("home") { HomeScreen(navController = navController) }
                        composable("home/{tourId}") { backStackEntry ->
                            val tourId = backStackEntry.arguments?.getString("tourId")?.toInt()
                            DetailScreen(tourId = tourId, navController = navController)
                        }

                    }
                }
            }
        }
    }
}
