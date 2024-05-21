package com.example.bottomnavigationcompose

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavigationHost( navController: NavHostController){

    NavHost(navController = navController, startDestination = ScreenDestination.HomeScreen.route){

        composable(route = ScreenDestination.HomeScreen.route){
            HomeScreen(navController = navController)
        }

        composable(route = ScreenDestination.EmailScreen.route){
            EmailScreen(navController = navController)
        }

        composable(route = ScreenDestination.SettingsScreen.route){
            SettingsScreen(navController = navController)
        }
    }
}