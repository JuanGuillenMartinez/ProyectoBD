package com.example.proyectobd.clases

import java.io.Serializable

class Usuario : Serializable {

    val correoElectronico: String
    val nombreUsuario: String
    val idUsuario: Int
    val firstName: String
    val lastName: String
    val isSuperuser: String
    val createdAt: String
    val isStaff: String
    val isActive: String

    constructor(id: Int, nombreUsuario: String, correo: String, firstName: String,
                lastName: String, isSuperuser: String, createdAt: String,
                isStaff: String, isActive: String) {

        this.correoElectronico=correo
        this.nombreUsuario = nombreUsuario
        this.idUsuario = id
        this.firstName = firstName
        this.lastName = lastName
        this.isSuperuser = isSuperuser
        this.createdAt = createdAt
        this.isStaff = isStaff
        this.isActive = isActive

    }

}