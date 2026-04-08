package com.nurfadhilahfajrin0047.waterintakeapp.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object About : Screen("about")
    object Materi : Screen("materi")
}