package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.rosadoOscuro
import com.example.myapplication.ui.theme.verdeOscuro
import com.example.myapplication.ui.theme.violetaOscuro

@Composable
fun Principal(navLogin:()-> Unit){
    Encabezado(navLogin)
    CuerpoPrincipal()
}


@Composable
fun Encabezado(navLogin: () -> Unit) {
    Row(Modifier.padding(10.dp,20.dp),
        horizontalArrangement = Arrangement.SpaceBetween) {
        Text("FloresCampo",
            fontSize = 30.sp,
            color = violetaOscuro,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

        var expanded by remember { mutableStateOf(false) }

        Box(modifier = Modifier.wrapContentSize()) {
            IconButton(onClick = { expanded = true }) {
                Icon(Icons.Default.MoreVert, contentDescription = "More options")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text("Cuenta") },
                    onClick = { expanded = false }
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
fun CuerpoPrincipal(){
    Column (
        modifier = Modifier.fillMaxSize().padding(10.dp,30.dp),
        horizontalAlignment = Alignment.CenterHorizontally){
        var remitente by remember { mutableStateOf("") }
        var destinatario by remember { mutableStateOf("")}
        var tipo by remember { mutableStateOf("")}
        var alergia by remember { mutableStateOf("")}
        var direccion by remember { mutableStateOf("")}
        var precio by remember { mutableStateOf("")}
        var metodo by remember { mutableStateOf("")}
        var flores by remember { mutableStateOf("")}
        var fecha by remember { mutableStateOf("")}
        var hora by remember { mutableStateOf("")}

        Spacer(modifier = Modifier.height(40.dp))
        Text("Nuevo registro",
            fontSize = 30.sp,
            color = rosadoOscuro,
            fontWeight = FontWeight.Bold,
        )
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
        OutlinedTextField(
            value = tipo,
            onValueChange = {tipo=it},
            label = {Text("Tipo de arreglo")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = flores,
            onValueChange = {flores=it},
            label = {Text("Preferencia de flores")},
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(10.dp))
        OutlinedTextField(
            value = alergia,
            onValueChange = {alergia=it},
            label = {Text("Restricciones alergias")},
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
            OutlinedTextField(
                value = metodo,
                onValueChange = {metodo=it},
                label = {Text("Método de pago")}
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {},
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = verdeOscuro
            )
        ) {
            Text("Guardar",
                fontSize= 20.sp)
        }
    }
}

/*@Preview(showSystemUi = true)
@Composable
fun previewPrincipal(){
    Principal()
}*/