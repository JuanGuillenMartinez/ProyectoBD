package com.example.proyectobd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_recycler.*

class RecyclerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler)

        val objeto = intent.extras
        val usuario: Usuario = objeto?.getSerializable("obj") as Usuario


        mostrarRecycler()

    }

    override fun onResume() {
        super.onResume()
        mostrarRecycler()
    }

    fun mostrarRecycler() {

        recyclerview_productos.layoutManager=LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val productos=importarProductos()
        val adaptador = Adaptador(productos)
        recyclerview_productos.adapter=adaptador

    }

    fun importarProductos() : ArrayList<Productos> {

        var lista = ArrayList<Productos>()
        var producto: Productos?
        val db: DatabaseHelper = DatabaseHelper(this)
        var id: Int
        var nombre: String
        var precio: Float
        var existencia: Int
        val resultado = db.obtenerDatos

        if (resultado.count == 0) {

            Toast.makeText(this, "No hay registros en la Base de datos ", Toast.LENGTH_LONG)

        } else {

            while(resultado.moveToNext()) {

                id=resultado.getString(0).toInt()
                nombre=resultado.getString(1)
                precio=resultado.getString(2).toFloat()
                existencia=resultado.getString(3).toInt()
                producto = Productos(id, nombre, precio, existencia, R.drawable.logo_tecnm)
                lista.add(producto)
                producto = null

            }

        }

        return lista

    }

}
