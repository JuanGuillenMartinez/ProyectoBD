package com.example.proyectobd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.androidnetworking.AndroidNetworking
import com.example.proyectobd.Productos
import com.example.proyectobd.R
import com.example.proyectobd.clases.DatabaseHelper
import com.example.proyectobd.webservice.Consultas
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class FormActivity : AppCompatActivity(), View.OnClickListener {

    internal var dbHelper = DatabaseHelper(this)

    fun mostrarToast(texto: String) {
        Toast.makeText(this@FormActivity, texto, Toast.LENGTH_LONG).show()
    }

    fun mostrarDialogo(titulo: String, mensaje: String) {

        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(titulo)
        builder.setMessage(mensaje)
        builder.show()

    }

    fun limpiarTexto() {
        txt_id.setText("")
        txt_nombre.setText("")
        txt_precio.setText("")
        txt_existencia.setText("")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val objeto = intent.extras
        val producto: Productos = objeto?.getSerializable("obj") as Productos
        txt_id.setText(producto.idProducto.toString())

        AndroidNetworking.initialize(getApplicationContext())
        btn_agregar.setOnClickListener(this)
        //insertarDatos()
        actualizarDatos()
        borrarDatos()
        mostrarTodos()

    }

    fun actualizarDatos() {
        btn_actualizar.setOnClickListener {
            try {
                val isUpdate = dbHelper.actualizar(txt_id.text.toString(),
                    txt_nombre.text.toString(),
                    txt_precio.text.toString(), txt_existencia.text.toString())
                if (isUpdate == true) {
                    mostrarToast("Actualizacion completada")
                    limpiarTexto()
                }
                else {
                    mostrarToast("Fallo al actualizar")
                }
            } catch (e: Exception){
                e.printStackTrace()
                mostrarToast(e.message.toString())
            }
        }
    }

    fun borrarDatos(){
        btn_eliminar.setOnClickListener {
            try {
                dbHelper.borrar(txt_id.text.toString())
                limpiarTexto()
            } catch (e: Exception){
                e.printStackTrace()
                mostrarToast(e.message.toString())
            }
        }
    }

    fun mostrarTodos() {
        btn_ver.setOnClickListener(
            View.OnClickListener {
                val res = dbHelper.obtenerDatos
                if (res.count == 0) {
                    mostrarDialogo("Error", "No hay datos encontrados")
                    return@OnClickListener
                }

                val buffer = StringBuffer()
                while (res.moveToNext()) {
                    buffer.append("ID: " + res.getString(0) + "\n")
                    buffer.append("Nombre: " + res.getString(1) + "\n")
                    buffer.append("Precio: " + res.getString(2) + "\n")
                    buffer.append("existencias: " + res.getString(3) + "\n\n")
                }
                mostrarDialogo("Mostrando informacion ", buffer.toString())
            }
        )
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_agregar ->{
            }
        }
    }

}
