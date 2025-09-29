package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
object Principal

@Composable
fun navegador(){
    val navController= rememberNavController()

    NavHost(navController=navController, startDestination = Login){
        composable<Login> {
            LoginScreen{
                navController.navigate(Principal)
            }
        }
        composable<Principal> {
            Principal{
                navController.navigate(Login)
            }
        }
    }
}