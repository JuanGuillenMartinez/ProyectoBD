package com.example.proyectobd.clases

import android.content.Context
import android.widget.Toast
import java.time.LocalDateTime

class Producto (var estado: Boolean, var codigo: String, var codigoBarra: String,
                var descripcion: String, var precio: Float, var existencia: Int, var marca: Int, var subcategoria: Int,
                var unidadMedida: Int, var usuarioCrea: Int) {

    var ultimaCompra: String
    var usuarioModifica: String
    var fechaModificacion: String
    var fechaCreacion: String
    var id_producto: Int

    init {
        this.ultimaCompra = "Indefinido"
        this.usuarioModifica = "Indefinido"
        this.fechaModificacion = "Indefinido"
        this.fechaCreacion = "Indefinido"
        this.id_producto = 1
    }

    fun idSiguiente() {

    }

    fun esCreado(fecha: String) {
        this.fechaCreacion = fecha
    }

    fun esModificado(usuarioModifica: String, fechaModificacion: String) {
        this.usuarioModifica = usuarioModifica
        this.fechaModificacion = fechaModificacion
    }

    fun esComprado(fechaCompra: String) {
        ultimaCompra = fechaCompra
    }

    fun mostrar(context: Context) {
        val texto = "$id_producto $estado $fechaCreacion $codigo $codigoBarra $descripcion $precio $existencia $marca $subcategoria $unidadMedida $usuarioCrea $usuarioModifica $ultimaCompra $fechaModificacion"
        Toast.makeText(context, texto, Toast.LENGTH_LONG).show()
        println(texto)
    }

}