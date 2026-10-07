package com.example.gempaduar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.gempaduar.ui.screens.DetailScreen
import com.example.gempaduar.ui.screens.HomeScreen
import com.example.gempaduar.ui.theme.GempaAppTheme
import com.example.gempaduar.ui.viewmodel.GempaViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GempaAppTheme {
                GempaApp()
            }
        }
    }
}

@Composable
fun GempaApp() {
    val navController = rememberNavController()
    val viewModel: GempaViewModel = viewModel()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                viewModel = viewModel,
                onItemClick = { id -> navController.navigate("detail/$id") }
            )
        }
        composable(
            route = "detail/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStackEntry ->
            DetailScreen(
                gempaId = backStackEntry.arguments?.getInt("id") ?: -1,
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }
    }
}