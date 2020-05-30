package com.example.proyectobd.clases

import java.security.MessageDigest

class Hasher {

    companion object {
        fun hash(input: String): String {
            val bytes = input.toByteArray()
            val md = MessageDigest.getInstance("SHA-256")
            val digest = md.digest(bytes)
            return digest.fold("", { str, it -> str + "%02x".format(it) })
        }
    }

}