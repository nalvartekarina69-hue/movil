package com.example.myapplication

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.rosadoOscuro
import com.google.firebase.Firebase
import com.google.firebase.auth.auth

@Composable
fun LoginScreen( navPrincipal:(String)-> Unit){
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
fun CuerpoLogin(navPrincipal: (String) -> Unit){

    val auth = Firebase.auth
    val activity = LocalView.current.context as Activity

    var correo by remember { mutableStateOf("") }
    var contrasena by remember { mutableStateOf("")}
    val contexto = LocalContext.current
    var pass_show by remember {mutableStateOf(false)}

    Column (
        modifier = Modifier.fillMaxSize().padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally){

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
            label = {Text("Correo electrónico")},
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "Usuario"
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
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = if (pass_show) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                val icono_eye = if (pass_show)painterResource(R.drawable.hide) else painterResource(R.drawable.view)
                IconButton(
                    onClick = {
                        pass_show = !pass_show
                    }

                ) {
                    Icon(painter = icono_eye,
                        contentDescription = null,
                        modifier = Modifier
                            .height(30.dp)
                            .width(30.dp),
                        tint = Color.Black
                    )
                }
            }

        )
        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
                if (contrasena !="" && correo != ""){
                    auth.signInWithEmailAndPassword(correo,contrasena)
                        .addOnCompleteListener(activity) { task ->
                            if (task.isSuccessful){
                                navPrincipal(correo)
                            }else{
                                Toast.makeText(contexto,
                                    "Usuario y/o contraseña inválidos", Toast.LENGTH_LONG).show()
                            }
                        }

                }else{
                    Toast.makeText(contexto,
                        "Usuario y/o contraseña requeridos", Toast.LENGTH_LONG).show()
                }

            },
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
