package com.example.proyectobd

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_productos.*

class ProductosActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_productos)

        mostrarRecycler()

    }

    fun mostrarRecycler() {

        recyclerview_productos.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val productos=ArrayList<Productos>()
        productos.add(Productos(1, "Laptop", 1500.0f, 3, R.drawable.login))
        productos.add(Productos(1, "Mouse", 1300.0f, 2, R.drawable.logo_tecnm))
        productos.add(Productos(1, "Lapiz", 1.0f, 1, R.drawable.producto))
        productos.add(Productos(1, "Refresco", 15.0f, 4, R.drawable.login))

        val adaptador = Adaptador(productos)
        recyclerview_productos.adapter=adaptador

    }
}
