package com.example.proyectobd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.proyectobd.R
import com.example.proyectobd.clases.Producto
import com.example.proyectobd.clases.Usuario
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val objeto = intent.extras
        val producto: Producto = objeto?.getSerializable("producto") as Producto
        val usuario: Usuario = objeto?.getSerializable("usuario") as Usuario

        mostrarProducto(producto)

        switch_modificar.setOnCheckedChangeListener { buttonView, isChecked ->
            if(switch_modificar.isChecked) {
                habilitarText(true)
                campo_usuariomodifica.setText(usuario.nombreUsuario)
            } else {
                habilitarText(false)
                campo_usuariomodifica.setText(producto.usuarioModifica)
            }
        }

    }

    fun habilitarText(estado: Boolean) {

        campo_id.isEnabled = estado
        campo_descripcion.isEnabled = estado
        campo_precio.isEnabled = estado
        campo_existencia.isEnabled = estado
        campo_codigo.isEnabled = estado
        campo_codigobarra.isEnabled = estado
        campo_fechacreacion.isEnabled = estado
        campo_fechamodificacion.isEnabled = estado
        campo_marca.isEnabled = estado
        campo_medida.isEnabled = estado
        campo_subcategoria.isEnabled = estado
        campo_ultimacompra.isEnabled = estado

    }

    fun mostrarProducto(producto: Producto) {

        campo_id.setText(producto.id_producto.toString())
        campo_descripcion.setText(producto.descripcion)
        campo_precio.setText(producto.precio.toString())
        campo_existencia.setText(producto.existencia.toString())
        campo_codigo.setText(producto.codigo)
        campo_codigobarra.setText(producto.codigoBarra)
        campo_fechacreacion.setText(producto.fechaCreacion)
        campo_fechamodificacion.setText(producto.fechaModificacion)
        campo_usuariomodifica.setText(producto.usuarioModifica)
        campo_usuariocrea.setText(producto.usuarioCrea.toString())
        campo_marca.setText(producto.marca.toString())
        campo_medida.setText(producto.unidadMedida.toString())
        campo_subcategoria.setText(producto.subcategoria.toString())
        campo_ultimacompra.setText(producto.ultimaCompra)
        img_foto.setImageResource(R.drawable.logo_tecnm)

        habilitarText(false)
        campo_usuariocrea.isEnabled = false
        campo_usuariomodifica.isEnabled = false

    }

}
