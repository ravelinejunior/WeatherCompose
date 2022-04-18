package br.com.raveline.weathercompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.raveline.weathercompose.presentation.view.MainScreen
import br.com.raveline.weathercompose.presentation.view.SearchScreen
import br.com.raveline.weathercompose.presentation.view.WeatherSplashScreen
import br.com.raveline.weathercompose.presentation.viewmodel.WeatherViewModel

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = WeatherScreens.SplashScreen.name
    ) {
        composable(WeatherScreens.SplashScreen.name){
            WeatherSplashScreen(navController = navController)
        }

        composable(WeatherScreens.MainScreen.name){
            val weatherViewModel = hiltViewModel<WeatherViewModel>()
            MainScreen(navController = navController,viewModel = weatherViewModel)
        }

        composable(WeatherScreens.SearchScreen.name){
            SearchScreen(navController = navController)
        }
    }
}