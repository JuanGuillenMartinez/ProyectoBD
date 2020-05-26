package com.example.proyectobd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectobd.R
import com.example.proyectobd.clases.Producto
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val objeto = intent.extras
        val producto: Producto = objeto?.getSerializable("obj") as Producto

        mostrarProducto(producto)

    }

    fun mostrarProducto(producto: Producto) {

        campo_id.text = producto.id_producto.toString()
        campo_descripcion.text = producto.descripcion
        campo_precio.text = producto.precio.toString()
        campo_existencia.text = producto.existencia.toString()
        campo_codigo.text = producto.codigo
        campo_codigobarra.text = producto.codigoBarra
        campo_fechacreacion.text = producto.fechaCreacion
        campo_fechamodificacion.text = producto.fechaModificacion
        campo_usuariomodifica.text = producto.usuarioModifica
        campo_usuariocrea.text = producto.usuarioCrea.toString()
        campo_marca.text = producto.marca.toString()
        campo_medida.text = producto.unidadMedida.toString()
        campo_subcategoria.text = producto.subcategoria.toString()
        campo_ultimacompra.text = producto.ultimaCompra
        img_foto.setImageResource(R.drawable.logo_tecnm)

    }

}
