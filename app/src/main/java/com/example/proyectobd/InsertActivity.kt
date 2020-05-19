package com.example.proyectobd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_insert.*

class InsertActivity : AppCompatActivity() , View.OnClickListener {

    internal var db = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        btn_agregar.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_agregar -> {
                insertarDatos()
            }
        }
    }

    fun insertarDatos() {
        val nombre = campo_nombre.text.toString()
        val precio = campo_precio.text.toString()
        val existencia = campo_existencia.text.toString()

        try {
            db.agregar(nombre, precio, existencia)
            mostrarToast("Producto agregado correctamente")
            limpiarTexto()
        } catch (e: Exception) {
            e.printStackTrace()
            mostrarToast(e.message.toString())
        }
    }

    private fun mostrarToast(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }

    private fun limpiarTexto() {
        campo_nombre.setText("")
        campo_precio.setText("")
        campo_existencia.setText("")
    }

}
