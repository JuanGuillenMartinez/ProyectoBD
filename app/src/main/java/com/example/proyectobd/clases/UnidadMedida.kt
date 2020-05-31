package com.example.proyectobd.clases

class UnidadMedida (var id: Int, var descripcion: String, var usuarioCrea: Usuario) {

    var estado: String
    var fechaCreacion: String
    var fechaModificacion: String
    lateinit var usuarioModifica: Usuario

    init {
        this.estado = "Indefinido"
        this.fechaCreacion = "Indefinido"
        this.fechaModificacion = "Indefinido"
    }

}