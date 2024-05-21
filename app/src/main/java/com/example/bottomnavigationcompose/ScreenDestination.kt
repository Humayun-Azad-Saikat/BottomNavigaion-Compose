package com.example.bottomnavigationcompose

sealed class ScreenDestination(val route:String){

    object HomeScreen:ScreenDestination("homescreen")
    object EmailScreen:ScreenDestination("emailscreen")
    object SettingsScreen:ScreenDestination("settingsscreen")

}
