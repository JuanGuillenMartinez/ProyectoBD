package com.example.proyectobd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectobd.Productos
import com.example.proyectobd.R
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val objeto = intent.extras
        val producto: Productos = objeto?.getSerializable("obj") as Productos

        mostrarProducto(producto)

    }

    fun mostrarProducto(producto: Productos) {

        campo_id.text = producto.idProducto.toString()
        campo_descripcion.text = producto.nombreProducto.toString()
        campo_precio.text = producto.precioProducto.toString()
        campo_existencia.text = producto.existencia.toString()

    }

}
