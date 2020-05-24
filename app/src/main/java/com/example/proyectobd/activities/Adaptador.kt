package com.example.proyectobd.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proyectobd.FormActivity
import com.example.proyectobd.InformationActivity
//import com.example.proyectobd.Productos
import com.example.proyectobd.R
import com.example.proyectobd.clases.Producto

class Adaptador(var lista: ArrayList<Producto>) : RecyclerView.Adapter<Adaptador.ViewHolder>() {


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {

        val context: Context = itemView.context

        fun bindItems (datos: Producto) {

            val producto = datos
            val descripcion:TextView = itemView.findViewById(R.id.textv_titulo)
            val precio:TextView = itemView.findViewById(R.id.textv_precio)
            val foto:ImageView = itemView.findViewById(R.id.thumbnail)
            val opciones:ImageView = itemView.findViewById(R.id.mn_opciones)

            descripcion.text = producto.descripcion
            precio.text = producto.id_producto.toString()
            producto.imagen = R.drawable.producto
            Glide.with(itemView.context).load(producto.imagen).into(foto)

            foto.setOnClickListener {
                Toast.makeText(itemView.context, "Producto: ${producto.descripcion} ", Toast.LENGTH_SHORT).show()
            }

            opciones.setOnClickListener {
                val popMenu = PopupMenu(context, opciones )

                popMenu.setOnMenuItemClickListener { item ->

                    when(item.itemId) {

                        R.id.mnitem_informacion -> {
                            val intent = Intent(context, InformationActivity::class.java)
                            val bundle = Bundle()
                            bundle.putSerializable("obj", producto)
                            intent.putExtras(bundle)
                            context.startActivity(intent)
                            true
                        }

                        R.id.mnitem_producto -> {
                            val intent = Intent(context, FormActivity::class.java)
                            val bundle = Bundle()
                            bundle.putSerializable("obj", producto)
                            intent.putExtras(bundle)
                            context.startActivity(intent)
                            true
                        }

                        else -> false

                    }
                }
                popMenu.inflate(R.menu.mn_opciones)
                popMenu.show()
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.content_item, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(lista[position])
    }
}