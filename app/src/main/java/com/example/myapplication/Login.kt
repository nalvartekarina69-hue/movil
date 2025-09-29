package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.rosadoOscuro

@Composable
fun LoginScreen( navPrincipal:()-> Unit){
    /*val image = painterResource(R.drawable.flores)
    Box() {
        Image(
            painter = image,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            alpha = 0.5F
        )

    }*/
    CuerpoLogin(navPrincipal)

}
@Composable
fun CuerpoLogin(navPrincipal: () -> Unit){
    Column (
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){
        var correo by remember { mutableStateOf("") }
        var contrasena by remember { mutableStateOf("")}
        Icon(imageVector = Icons.Default.AccountCircle,
            contentDescription = "login",
            modifier = Modifier
                .height(100.dp)
                .width(100.dp),
            tint = Color.DarkGray)
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(
            value = correo,
            onValueChange = {correo=it},
            label = {Text("Correo")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Correo"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = contrasena,
            onValueChange = {contrasena=it},
            label = {Text("Contraseña")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "key"
                )
            },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(30.dp))
        Button(
            onClick = {navPrincipal()},
            modifier = Modifier.padding(20.dp).fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = rosadoOscuro
            )
        ) {
            Text("Acceder",
                fontSize= 20.sp)
        }
    }
}

/*@Preview(showSystemUi = true)
@Composable
fun previewLogin(){
    LoginScreen()
}*/
