package com.nurfadhilahfajrin0047.waterintakeapp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nurfadhilahfajrin0047.waterintakeapp.screen.MainScreen
import com.nurfadhilahfajrin0047.waterintakeapp.screen.AboutScreen

@Composable
fun SetupNavGraph(navController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route){
            MainScreen(navController)
        }
        composable(Screen.About.route) {
            AboutScreen(navController)
        }
    }
}