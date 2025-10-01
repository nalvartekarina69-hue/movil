package com.example.myapplication

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.example.myapplication.ui.theme.rosadoOscuro
import com.example.myapplication.ui.theme.verdeOscuro
import com.example.myapplication.ui.theme.violetaOscuro

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EditScreen(correo: String,
               pedidos: Pedidos,
               navLogin: () -> Unit,
               navPrincipal: () -> Unit,
               navLista:()-> Unit) {
    Encabezado3(correo, navLogin, navPrincipal)
    CuerpoEdit(pedidos,navLista)

}


@Composable
fun Encabezado3(correo: String, navLogin: () -> Unit, navPrincipal: () -> Unit) {

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

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CuerpoEdit(pedido: Pedidos, navLista: () -> Unit) {
    Column (
        modifier = Modifier.fillMaxSize().padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center){
        //estados
        var remitente by remember { mutableStateOf(pedido.remitente) }
        var destinatario by remember { mutableStateOf(pedido.destinatario)}
        var direccion by remember { mutableStateOf(pedido.direccion)}
        var precio by remember { mutableStateOf(pedido.precio)}
        var flores by remember { mutableStateOf(pedido.flores)}
        var fecha by remember { mutableStateOf(pedido.fecha)}
        var hora by remember { mutableStateOf(pedido.hora)}
        //select metodo
        var expandedMetodo by remember { mutableStateOf(false) }
        val itemsMetodo = listOf("Efectivo", "Tarjeta", "Yape/Plin")
        var selectedItemMetodo by remember { mutableStateOf(pedido.metodo) }
        //select tipo
        var expandedTipo by remember { mutableStateOf(false) }
        val itemsTipo = listOf("Corona", "Centro de mesa", "Bouquet")
        var selectedItemTipo by remember { mutableStateOf(pedido.tipo) }

        Spacer(modifier = Modifier.height(40.dp))
        Text("Editar pedido",
            fontSize = 30.sp,
            color = rosadoOscuro,
            fontWeight = FontWeight.Bold,
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = remitente,
            onValueChange = {remitente=it},
            label = {Text("Remitente")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = destinatario,
            onValueChange = {destinatario=it},
            label = {Text("Destinatario")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        ExposedDropdownMenuBox(
            expanded = expandedTipo,
            onExpandedChange = { expandedTipo = !expandedTipo }
        ) {
            OutlinedTextField(
                value = selectedItemTipo,
                onValueChange = {},
                readOnly = true,
                label = { Text("Selecciona un tipo") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedTipo) },
                modifier = Modifier.fillMaxWidth().menuAnchor()
            )

            ExposedDropdownMenu(
                expanded = expandedTipo,
                onDismissRequest = { expandedTipo = false }
            ) {
                itemsTipo.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(item) },
                        onClick = {
                            selectedItemTipo = item
                            expandedTipo = false
                        }
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = flores,
            onValueChange = {flores=it},
            label = {Text("Preferencia de flores")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = direccion,
            onValueChange = {direccion=it},
            label = {Text("Dirección de entrega")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = fecha,
                onValueChange = {fecha=it},
                label = {Text("Fecha")},
                modifier = Modifier.width(250.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            OutlinedTextField(
                value = hora,
                onValueChange = {hora=it},
                label = {Text("Hora")}
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            OutlinedTextField(
                value = precio,
                onValueChange = {precio=it},
                label = {Text("Precio")},
                modifier = Modifier.width(150.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            ExposedDropdownMenuBox(
                expanded = expandedMetodo,
                onExpandedChange = { expandedMetodo = !expandedMetodo }
            ) {
                OutlinedTextField(
                    value = selectedItemMetodo,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Selecciona un tipo") },
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedMetodo) },
                    modifier = Modifier.fillMaxWidth().menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expandedMetodo,
                    onDismissRequest = { expandedMetodo = false }
                ) {
                    itemsMetodo.forEach { item ->
                        DropdownMenuItem(
                            text = { Text(item) },
                            onClick = {
                                selectedItemMetodo = item
                                expandedMetodo = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {

                FirebaseRepository.updatePedido(Pedidos(pedido.id, remitente,destinatario,direccion,precio,flores,selectedItemMetodo,selectedItemTipo,fecha,hora))
                navLista()

            },
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = verdeOscuro
            )
        ) {
            Text("Actualizar",
                fontSize= 20.sp)
        }

    }
}