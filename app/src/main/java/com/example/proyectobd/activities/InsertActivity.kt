package com.example.proyectobd.activities

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.androidnetworking.AndroidNetworking
import com.cloudinary.android.MediaManager
import com.example.proyectobd.R
import com.example.proyectobd.clases.DatabaseHelper
import com.example.proyectobd.clases.Galeria
import com.example.proyectobd.clases.Preference
import com.example.proyectobd.clases.Producto
import com.example.proyectobd.webservice.ConsultaProducto
import kotlinx.android.synthetic.main.activity_insert.*


class InsertActivity : AppCompatActivity() , View.OnClickListener {

    internal var db = DatabaseHelper(this)
    var estado: String = "true"
    val peticionCodigo = 10
    val peticionTomarFoto = Galeria.peticionTomarFoto
    var foto: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        btn_agregar.setOnClickListener(this)
        imageButton_barras.setOnClickListener(this)
        imgbtn_foto_producto.setOnClickListener(this)
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
                btn_agregar.isEnabled = false
            }
            R.id.imageButton_barras -> {
                val intent = Intent(this, SimpleScannerActivity::class.java)
                startActivityForResult(intent, peticionCodigo)
            }
            R.id.imgbtn_foto_producto -> {

                abrirCamara()

            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == peticionCodigo) {
            if(resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("codigo")
                campo_codigobarra.setText(result)
            }
        }
        if(requestCode == peticionTomarFoto) {
            if(resultCode == Activity.RESULT_OK) {
                img_foto.setImageURI(foto)
                val requestId: String = MediaManager.get().upload(foto).option("public_id", "12345").option("folder", "Proyecto/Productos/").dispatch();
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

    fun abrirCamara() {
        val permiso = arrayOf(android.Manifest.permission.CAMERA, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permiso, peticionTomarFoto)
        } else {
            this.foto = Galeria.tomarFoto(this)
            val camaraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            camaraIntent.putExtra(MediaStore.EXTRA_OUTPUT, foto)
            startActivityForResult(camaraIntent, peticionTomarFoto)
        }
    }



}
