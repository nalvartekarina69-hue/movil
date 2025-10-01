package com.example.myapplication

import com.google.firebase.database.Exclude
import java.time.LocalDateTime

data class Pedidos(
    val id: String = "",
    val remitente: String ="",
    val destinatario: String="",
    val direccion: String="",
    val precio: String="",
    val flores: String="",
    val metodo: String="",
    val tipo: String="",
    val fecha: String="",
    val hora: String="",
)
