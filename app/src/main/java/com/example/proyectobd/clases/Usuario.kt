package com.example.proyectobd.clases

import java.io.Serializable

class Usuario : Serializable {

    val correoElectronico: String
    val contraseña: String

    constructor(correo: String, pass: String) {
        this.correoElectronico=correo
        this.contraseña=pass
    }

}