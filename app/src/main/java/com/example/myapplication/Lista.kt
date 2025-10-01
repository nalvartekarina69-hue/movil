package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.Pink80
import com.example.myapplication.ui.theme.verdeOscuro
import com.example.myapplication.ui.theme.violetaOscuro
@Composable
fun ListaScreen(correo:String,
                navLogin:()-> Unit,
                navPrincipal:() -> Unit,
                navEdit: (Pedidos) -> Unit){
    Encabezado2(correo, navLogin, navPrincipal)
    CuerpoLista(navEdit)
}

@Composable
fun Encabezado2(correo: String, navLogin: () -> Unit, navPrincipal: () -> Unit) {

    Row(Modifier.fillMaxWidth()
        .background(violetaOscuro)
        .padding(0.dp, 20.dp, 0.dp, 0.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
        Text("FloresCampo",
            fontSize = 30.sp,
            color = Color.White,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(10.dp,20.dp)
        )


        var expanded by remember { mutableStateOf(false) }


        Row(modifier = Modifier, verticalAlignment = Alignment.CenterVertically) {
            Text(correo,
                color = Color.White,
                modifier = Modifier.width(60.dp), // Set a fixed width
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options",
                    tint = Color.White)
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Registro") },
                    onClick = { navPrincipal()}
                )
                DropdownMenuItem(
                    text = { Text("Salir") },
                    onClick = { navLogin() }
                )
            }
        }
    }
}

@Composable
fun CuerpoLista(navEdit: (Pedidos) -> Unit){

    val pedidos= remember { mutableStateListOf<Pedidos>()}

    LaunchedEffect(Unit) {
        FirebaseRepository.getPedidos {
            pedidos.clear()
            pedidos.addAll(it)
        }


    }
    Column(modifier = Modifier.padding(0.dp, 100.dp, 0.dp, 0.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text("Lista de pedidos",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold)
        LazyColumn{
            items(pedidos) { pedido ->
                Card(
                    Modifier.fillMaxWidth().padding(20.dp, 5.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color.LightGray,),
                    elevation = CardDefaults.cardElevation(4.dp),

                ) {
                    Column (Modifier.padding(10.dp)){
                        Text("Remitente: ${pedido.remitente}",
                            Modifier.padding(5.dp, 5.dp))
                        Text("Destinatario: ${pedido.destinatario}",
                            Modifier.padding(5.dp, 5.dp))
                        Text("Dirección: ${pedido.direccion}",
                            Modifier.padding(5.dp, 5.dp))
                        Text("Tipo de arreglo: ${pedido.tipo}",
                            Modifier.padding(5.dp, 5.dp))
                        Row(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Precio: ${pedido.precio}",
                                Modifier.padding(5.dp, 5.dp))
                            Text("Método de pago: ${pedido.metodo}",
                                Modifier.padding(5.dp, 5.dp))

                        }
                        Row(Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween) {
                            Text("Fecha: ${pedido.fecha}",
                                Modifier.padding(5.dp, 5.dp),
                                color = violetaOscuro,fontSize = 20.sp, fontWeight = FontWeight.Bold)
                            Text("Hora: ${pedido.hora}",
                                Modifier.padding(5.dp, 5.dp),
                                color = violetaOscuro,fontSize = 20.sp,fontWeight = FontWeight.Bold)

                        }

                        Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround){
                            Icon(imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(25.dp)
                                    .clickable(
                                        onClick = {
                                            FirebaseRepository.deletePedido(pedido.id)}
                                    ),
                                tint = Color.Red)
                            Icon(imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                modifier = Modifier
                                    .height(25.dp)
                                    .width(25.dp)
                                    .clickable(
                                        onClick = {
                                            navEdit(pedido)
                                        }
                                    ),
                                tint = verdeOscuro)
                        }
                    }


                }
            }
        }
    }

}