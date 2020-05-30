package com.example.proyectobd.webservice

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.ANResponse
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.proyectobd.activities.InformationActivity
import com.example.proyectobd.clases.Adaptador
import com.example.proyectobd.clases.Producto
import com.example.proyectobd.clases.Usuario
import kotlinx.android.synthetic.main.activity_recycler.*
import org.json.JSONArray
import org.json.JSONObject

class ConsultaPoducto (contexto: Context) {

    val context: Context
    val url_insert: String
    val url_allProductos: String
    val url_delete: String
    val url_update: String

    init {
        context = contexto
        url_insert = "https://mysterious-woodland-17155.herokuapp.com/api_rest/INSERT_producto_POST.php"
        url_allProductos = "https://mysterious-woodland-17155.herokuapp.com/api_rest/SELECT_ALL_producto_GET.php"
        url_delete = "https://mysterious-woodland-17155.herokuapp.com/api_rest/DELETE_producto_POST.php"
        url_update = "https://mysterious-woodland-17155.herokuapp.com/api_rest/UPDATE_productos_POST.php"
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

    fun eliminarProducto(usuarioModifica: String, idProducto: String) {

        AndroidNetworking.post(url_delete)
            .addBodyParameter("usuarioModifica", usuarioModifica)
            .addBodyParameter("idProducto", idProducto)
            .setPriority(Priority.LOW)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject) {
                    Toast.makeText(context, response.getString("estado"), Toast.LENGTH_LONG).show()
                    (context as Activity).finish()
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(context, "Error " + anError.errorDetail, Toast.LENGTH_LONG)
                        .show()
                }

            })

    }

    fun actualizarProducto(producto: Producto) {

        AndroidNetworking.post(url_update)
            .addBodyParameter("idProducto", producto.id_producto.toString())
            .addBodyParameter("descripcion", producto.descripcion)
            .addBodyParameter("precio", producto.precio.toString())
            .addBodyParameter("existencia", producto.existencia.toString())
            .addBodyParameter("codigo", producto.codigo)
            .addBodyParameter("codigoBarras", producto.codigoBarra)
            .addBodyParameter("marcaId", producto.marca.toString())
            .addBodyParameter("subcategoriaId", producto.subcategoria.toString())
            .addBodyParameter("unidadMedida", producto.unidadMedida.toString())
            .addBodyParameter("usuarioModifica", producto.usuarioModifica)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject) {
                    Toast.makeText(context, response.getString("estado"), Toast.LENGTH_LONG ).show()
                    (context as Activity).finish()
                    val intent = context.intent
                    val bundle = Bundle()
                    bundle.putSerializable("producto", producto)
                    intent.putExtras(bundle)
                    context.startActivity(intent)
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(context, "Error " + anError.errorDetail, Toast.LENGTH_LONG ).show()
                }

            })

    }

    fun obtenerProductos(recycler: RecyclerView) {

        val productos = ArrayList<Producto>()

        AndroidNetworking.get(url_allProductos)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object : JSONObjectRequestListener {

                override fun onResponse(response: JSONObject) {
                    val arrayProductos: JSONArray = response.getJSONArray("data")
                    for (i in 0 until arrayProductos.length()) {
                        val objeto = arrayProductos.getJSONObject(i)
                        val id= objeto.getString("id").toInt()
                        val estado = objeto.getString("estado")
                        val fechaCreacion= objeto.getString("fechaCreacion")
                        val fechaModificacion= objeto.getString("fechaModificacion")
                        val usuarioModifica = objeto.getString("usuarioModifica")
                        val codigo = objeto.getString("codigo")
                        val codigoBarra = objeto.getString("codigo_barra")
                        val descripcion = objeto.getString("descripcion")
                        val precio = objeto.getString("precio").toFloat()
                        val existencia = objeto.getString("existencia").toInt()
                        val ultimaCompra = objeto.getString("ultima_compra")
                        val marca = objeto.getString("marca_id").toInt()
                        val subcategoria = objeto.getString("subcategoria_id").toInt()
                        val unidadMedida = objeto.getString("unidad_medida_id").toInt()
                        val usuarioCrea = objeto.getString("usuarioCrea_id").toInt()
                        var producto: Producto = Producto(estado, codigo, codigoBarra, descripcion, precio, existencia, marca,
                            subcategoria, unidadMedida, usuarioCrea )
                        producto.ultimaCompra = ultimaCompra
                        producto.usuarioModifica = usuarioModifica
                        producto.fechaCreacion = fechaCreacion
                        producto.id_producto = id
                        producto.fechaModificacion = fechaModificacion
                        productos.add(producto)

                    }

                    recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val adaptador = Adaptador(productos)
                    recycler.adapter = adaptador

                }

                override fun onError(anError: ANError) {
                    Toast.makeText(context, "Error " + anError.errorDetail + anError.errorBody, Toast.LENGTH_LONG ).show()
                }

            })

    }

}