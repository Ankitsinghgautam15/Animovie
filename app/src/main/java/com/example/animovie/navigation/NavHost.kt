package com.example.animovie.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Banner Screen") {
        composable("Banner Screen") {
            BuildScreen(navHostController = navController, modifier = Modifier)

        }
        composable("Movie Screen") {
            MovieScreen(navHostController = navController)

        }

    }
}

