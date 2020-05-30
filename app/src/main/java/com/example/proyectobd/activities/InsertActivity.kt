package com.example.proyectobd.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.example.proyectobd.R
import com.example.proyectobd.clases.DatabaseHelper
import com.example.proyectobd.clases.Preference
import com.example.proyectobd.clases.Producto
import com.example.proyectobd.webservice.ConsultaProducto
import kotlinx.android.synthetic.main.activity_insert.*

class InsertActivity : AppCompatActivity() , View.OnClickListener {

    internal var db = DatabaseHelper(this)
    var estado: String = "false"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        btn_agregar.setOnClickListener(this)
        imageButton_barras.setOnClickListener(this)
        AndroidNetworking.initialize(getApplicationContext())
        switch_estatus.setOnCheckedChangeListener { buttonView, isChecked ->
            if(switch_estatus.isChecked) {
                switch_estatus.text = "Activo"
                estado = "true"
            } else {
                switch_estatus.text = "Inactivo"
                estado = "false"
            }
        }
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_agregar -> {
                val conexion = ConsultaProducto(this)
                val producto = crearProducto()
                conexion.registrarProducto(producto)
            }
            R.id.imageButton_barras -> {
                val intent = Intent(this, SimpleScannerActivity::class.java)
                startActivityForResult(intent, 1)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 1) {
            if(resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("codigo")
                campo_codigobarra.setText(result)
            }
        }
    }

    fun crearProducto() : Producto {

        val descripcion = campo_descripcion.text.toString()
        val codigo = campo_codigo.text.toString()
        val codigoBarras = campo_codigobarra.text.toString()
        val estado = this.estado
        val marca = campo_marca.text.toString().toInt()
        val unidadMedida = campo_medida.text.toString().toInt()
        val subcategoria = campo_subcategoria.text.toString().toInt()
        val precio = campo_precio.text.toString().toFloat()
        val existencia = campo_existencia.text.toString().toInt()
        val usuarioCrea = Preference(this).getId()

        val producto = Producto(estado, codigo, codigoBarras, descripcion, precio, existencia, marca, subcategoria, unidadMedida, usuarioCrea)

        return producto

    }

    /*fun insertarDatos() {
        val nombre = campo_descripcion.text.toString()
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
    }*/

    private fun mostrarToast(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_LONG).show()
    }

    private fun limpiarTexto() {
        campo_descripcion.setText("")
        campo_precio.setText("")
        campo_existencia.setText("")
    }

}
