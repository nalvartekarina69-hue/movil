package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
//object Principal
data class Principal(val correo: String)
@Composable
fun navegador(){
    val navController= rememberNavController()

    NavHost(navController=navController, startDestination = Login){
        composable<Login> {
            LoginScreen{correo->
                navController.navigate(Principal(correo = correo))
            }
        }
        composable<Principal> {
            backStackEntry-> val parametro: Principal = backStackEntry.toRoute()
            Principal(parametro.correo){
                navController.navigate(Login)
            }
        }
    }
}