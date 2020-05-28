package com.example.proyectobd.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.proyectobd.R
import com.example.proyectobd.clases.Usuario
import com.example.proyectobd.clases.Adaptador
import com.example.proyectobd.clases.Producto
import kotlinx.android.synthetic.main.activity_recycler.*
import org.json.JSONArray
import org.json.JSONObject

class RecyclerActivity : AppCompatActivity() {

    val contexto = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        //val objeto = intent.extras
        //val usuario: Usuario = objeto?.getSerializable("obj") as Usuario
        AndroidNetworking.initialize(applicationContext)
        mostrarRecycler()

    }

    override fun onResume() {
        super.onResume()
        mostrarRecycler()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inf = menuInflater
        inf.inflate(R.menu.mn_insertar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.mnitem_producto -> {

                val intent = Intent(this, InsertActivity::class.java)
                this.startActivity(intent)

                true
            }

            else -> false
        }

        return super.onOptionsItemSelected(item)
    }

    fun mostrarRecycler() {

        importarProductos()

    }

    fun importarProductos() {

        val productos = ArrayList<Producto>()

        AndroidNetworking.get("https://mysterious-woodland-17155.herokuapp.com/api_rest/SELECT_ALL_producto_GET.php")
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
                        var producto: Producto = Producto(estado, codigo, codigoBarra, descripcion, precio, existencia, marca, subcategoria, unidadMedida, usuarioCrea)
                        producto.ultimaCompra = ultimaCompra
                        producto.usuarioModifica = usuarioModifica
                        producto.fechaCreacion = fechaCreacion
                        producto.id_producto = id
                        producto.fechaModificacion = fechaModificacion
                        productos.add(producto)

                        recyclerview_productos.layoutManager = LinearLayoutManager(contexto, LinearLayoutManager.VERTICAL, false)
                        val adaptador =
                            Adaptador(productos)
                        recyclerview_productos.adapter = adaptador

                    }
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(contexto, "Error " + anError.errorDetail + anError.errorBody, Toast.LENGTH_LONG ).show()
                }

            })

    }

}
