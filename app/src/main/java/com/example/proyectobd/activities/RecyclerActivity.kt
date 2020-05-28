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
import com.example.proyectobd.webservice.Consultas
import kotlinx.android.synthetic.main.activity_recycler.*
import org.json.JSONArray
import org.json.JSONObject

class RecyclerActivity : AppCompatActivity() {

    val contexto = this
    lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)
        val datos = intent.extras
        usuario = datos?.getSerializable("obj") as Usuario
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

        val consulta = Consultas(this)
        consulta.obtenerProductos(recyclerview_productos, usuario)

    }

}
