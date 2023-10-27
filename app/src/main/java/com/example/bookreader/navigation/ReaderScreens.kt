package com.example.bookreader.navigation

import com.example.bookreader.screens.stats.StatsScreen

enum class ReaderScreens {
    SplashScreen,
    LoginScreen,
    CreateAccountScreen,
    ReaderHomeScreen,
    ReaderStatScreen,
    SearchScreen,
    DetailScreen,
    UpdateScreen;

    companion object{
        fun fromRoute(route: String): ReaderScreens
        = when(route?.substringBefore("/")){
            SplashScreen.name -> SplashScreen
            LoginScreen.name -> LoginScreen
            CreateAccountScreen.name -> ReaderHomeScreen
            SearchScreen.name -> SearchScreen
            DetailScreen.name -> DetailScreen
            UpdateScreen.name -> UpdateScreen
            ReaderStatScreen.name -> ReaderStatScreen
            null ->ReaderHomeScreen
            else -> throw IllegalArgumentException("Route $route is not recognized")
        }
    }
}