package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object Login

@Serializable
data class Principal(val correo: String)

@Serializable
data class Lista(val correo: String)

@Serializable
data class Edit(
    val correo: String,
    val id: String,
    val remitente: String,
    val destinatario: String,
    val direccion: String,
    val precio: String,
    val flores: String,
    val metodo: String,
    val tipo: String,
    val fecha: String,
    val hora: String
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun navegador(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Login){
        composable<Login> {
            LoginScreen { correo ->
                navController.navigate(Principal(correo = correo)){
                    popUpTo<Login> { inclusive = true }
                }
            }
        }

        composable<Principal> { backStackEntry ->
            val parametro: Principal = backStackEntry.toRoute()
            PrincipalScreen(
                correo = parametro.correo,
                navLogin = { navController.navigate(Login) },
                navLista = { navController.navigate(Lista(correo = parametro.correo)) }
            )
        }

        composable<Lista> { backStackEntry ->
            val parametro: Lista = backStackEntry.toRoute()
            ListaScreen(
                correo = parametro.correo,
                navLogin = { navController.navigate(Login) },
                navPrincipal = { navController.navigate(Principal(correo = parametro.correo)) },
                navEdit = { pedido ->
                    navController.navigate(
                        Edit(
                            correo = parametro.correo,
                            id = pedido.id,
                            remitente = pedido.remitente,
                            destinatario = pedido.destinatario,
                            direccion = pedido.direccion,
                            precio = pedido.precio,
                            flores = pedido.flores,
                            metodo = pedido.metodo,
                            tipo = pedido.tipo,
                            fecha = pedido.fecha,
                            hora = pedido.hora
                        )
                    )
                }
            )
        }

        composable<Edit> { backStackEntry ->
            val parametro: Edit = backStackEntry.toRoute()
            EditScreen(
                correo = parametro.correo,
                pedidos = Pedidos(
                    id = parametro.id,
                    remitente = parametro.remitente,
                    destinatario = parametro.destinatario,
                    direccion = parametro.direccion,
                    precio = parametro.precio,
                    flores = parametro.flores,
                    metodo = parametro.metodo,
                    tipo = parametro.tipo,
                    fecha = parametro.fecha,
                    hora = parametro.hora
                ),
                navLogin = { navController.navigate(Login) },
                navPrincipal = { navController.navigate(Principal(correo = parametro.correo)) },
                navLista = { navController.navigate(Lista(correo = parametro.correo)) }
            )
        }
    }
}