package com.example.proyectobd.webservice

import android.content.Context
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.proyectobd.clases.Producto
import org.json.JSONObject

class Consultas (contexto: Context) {

    val context: Context
    val url_insert: String

    init {
        context = contexto
        url_insert = "https://mysterious-woodland-17155.herokuapp.com/rest/INSERT_producto_POST.php"
    }



    fun agregarProducto(nombre: String, precio: String, categoria: String) {

        AndroidNetworking.post(url_insert)
            .addBodyParameter("descripcion", nombre)
            .addBodyParameter("precio", precio)
            .addBodyParameter("categoria", categoria)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject) {
                    Toast.makeText(context, " Producto ingresado correctamente. \nID ingresado: ${response.getString("id")} ", Toast.LENGTH_LONG ).show()
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(context, "Error " + anError.errorDetail, Toast.LENGTH_LONG ).show()
                }

            })
    }

    fun registrarProducto(producto: Producto) {

        AndroidNetworking.post(url_insert)
            .addBodyParameter("estado", producto.estado.toString())
            .addBodyParameter("codigo", producto.codigo)
            .addBodyParameter("codigoBarra", producto.codigoBarra)
            .addBodyParameter("descripcion", producto.descripcion)
            .addBodyParameter("precio", producto.precio.toString())
            .addBodyParameter("existencia", producto.existencia.toString())
            .addBodyParameter("marcaId", producto.marca.toString())
            .addBodyParameter("subcategoriaId", producto.subcategoria.toString())
            .addBodyParameter("unidadMedida", producto.unidadMedida.toString())
            .addBodyParameter("usuarioId", producto.usuarioCrea.toString())
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject) {
                    Toast.makeText(context, response.getString("estado"), Toast.LENGTH_LONG ).show()
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(context, "Error " + anError.errorDetail, Toast.LENGTH_LONG ).show()
                }

            })

    }
}