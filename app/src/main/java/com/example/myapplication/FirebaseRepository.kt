package com.example.myapplication

import androidx.compose.runtime.mutableStateListOf
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

object FirebaseRepository {
    private val db = FirebaseDatabase.getInstance().getReference("pedidos")

    fun addPedido(pedido: Pedidos){
        val id = db.push().key!!
        db.child(id).setValue(pedido.copy(id=id))
    }

    fun updatePedido(pedido: Pedidos){
        db.child(pedido.id).setValue(pedido)
    }

    fun deletePedido(id: String){
        db.child(id).removeValue()
    }

    fun getPedidos(onDataChange:(List<Pedidos>)->Unit){
        db.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val pedidoList = mutableStateListOf<Pedidos>()
                snapshot.children.forEach{
                    it.getValue(Pedidos::class.java)?.let { pedido -> pedidoList.add(pedido) }
                }
                onDataChange(pedidoList)
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}