package com.example.proyectobd.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.proyectobd.R
import com.example.proyectobd.clases.Preference
import com.example.proyectobd.clases.Producto
import com.example.proyectobd.webservice.ConsultaProducto
import kotlinx.android.synthetic.main.activity_information.*

class InformationActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var producto: Producto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)
        val objeto = intent.extras
        producto = objeto?.getSerializable("producto") as Producto
        mostrarProducto(producto)
        changeSwitch(producto)
        btn_eliminar.setOnClickListener(this)
        btn_actualizar.setOnClickListener(this)
        imgbtn_barra.setOnClickListener(this)
    }

    override fun onClick(v: View) {

        when(v.id) {
            R.id.btn_eliminar -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setMessage("¿Desea eliminar el producto?")
                builder.setCancelable(false)
                builder.setPositiveButton("Si, estoy seguro") { dialog, which ->
                    val consulta = ConsultaProducto(this)
                    val usuarioModifica = Preference(this).getId().toString()
                    Toast.makeText(this, "usuarioModifica\n${producto.id_producto}", Toast.LENGTH_LONG).show()
                    consulta.eliminarProducto(usuarioModifica,producto.id_producto.toString())
                }
                builder.setNegativeButton("No") { dialog, which ->
                    dialog.cancel()
                }
                val dialog = builder.create()
                dialog.show()
            }

            R.id.btn_actualizar -> {
                val builder: AlertDialog.Builder = AlertDialog.Builder(this)
                builder.setMessage("¿Desea actualizar la informacion del producto?")
                builder.setCancelable(false)
                builder.setPositiveButton("Si, estoy seguro") { dialog, which ->
                    val consulta = ConsultaProducto(this)
                    val usuarioModifica = Preference(this).getId().toString()
                    val aux = auxiliarActualizar()
                    consulta.actualizarProducto(aux)
                }
                builder.setNegativeButton("No") { dialog, which ->
                    dialog.cancel()
                }
                val dialog = builder.create()
                dialog.show()
            }

            R.id.imgbtn_barra -> {
                val intent = Intent(this, SimpleScannerActivity::class.java)
                startActivityForResult(intent, 2)

            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == 2) {
            if(resultCode == Activity.RESULT_OK) {
                val result = data?.getStringExtra("codigo")
                campo_codigobarra.setText(result)
            }
        }
    }

    fun changeSwitch(producto: Producto) {

        switch_modificar.setOnCheckedChangeListener { buttonView, isChecked ->
            if (switch_modificar.isChecked) {
                val username: String? = Preference(this).getUsername()
                habilitarText(true)
                campo_usuariomodifica.setText(username)
            } else {
                habilitarText(false)
                mostrarProducto(producto)
            }
        }

    }

    fun habilitarText(estado: Boolean) {

        campo_descripcion.isEnabled = estado
        campo_precio.isEnabled = estado
        campo_existencia.isEnabled = estado
        campo_codigo.isEnabled = estado
        campo_codigobarra.isEnabled = estado
        campo_marca.isEnabled = estado
        campo_medida.isEnabled = estado
        campo_subcategoria.isEnabled = estado
        btn_actualizar.isEnabled = estado
        imgbtn_barra.isEnabled = estado
        campo_usuariocrea.isEnabled = false
        campo_usuariomodifica.isEnabled = false
        campo_ultimacompra.isEnabled = false
        campo_fechacreacion.isEnabled = false
        campo_fechamodificacion.isEnabled = false
        campo_id.isEnabled = false

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
        Glide.with(this).load(producto.imagen).into(img_foto)

        habilitarText(false)
        btn_actualizar.isEnabled = false
        imgbtn_barra.isEnabled = false

    }

    fun auxiliarActualizar() : Producto {

        val descripcion = campo_descripcion.text.toString()
        val codigo = campo_codigo.text.toString()
        val codigoBarras = campo_codigobarra.text.toString()
        val estado = true.toString()
        val marca = campo_marca.text.toString().toInt()
        val unidadMedida = campo_medida.text.toString().toInt()
        val subcategoria = campo_subcategoria.text.toString().toInt()
        val precio = campo_precio.text.toString().toFloat()
        val existencia = campo_existencia.text.toString().toInt()
        val usuarioCrea = campo_usuariocrea.text.toString().toInt()
        val producto = Producto(estado, codigo, codigoBarras, descripcion, precio, existencia, marca, subcategoria, unidadMedida, usuarioCrea)
        producto.id_producto = campo_id.text.toString().toInt()
        producto.usuarioModifica = Preference(this).getId().toString()
        return producto

    }

}
