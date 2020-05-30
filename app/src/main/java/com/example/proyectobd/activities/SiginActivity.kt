package com.example.proyectobd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.proyectobd.R
import com.example.proyectobd.clases.DatabaseHelper
import com.example.proyectobd.clases.Hasher
import com.example.proyectobd.webservice.ConsultaUsuario
import kotlinx.android.synthetic.main.activity_sigin.*

class SiginActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sigin)
        btn1.setOnClickListener(this)
        btn2.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn1 -> {
                val consulta = ConsultaUsuario(this)
                val username: String = campo_nombre_usuario.text.toString()
                val contraseña: String = campo_contraseña.text.toString()
                val contraseñaRep: String = campo_repcontraseña.text.toString()

                if (validarCampoContraseña(contraseña, contraseñaRep)) {
                    val consulta = ConsultaUsuario(this)
                    val username = campo_nombre_usuario.text.toString()
                    val correo = campo_correo.text.toString()
                    val contraseña = Hasher.hash(campo_contraseña.text.toString())
                    consulta.registrarUsuario(username, correo, contraseña)
                } else {
                    Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG)
                }

            }
            R.id.btn2 -> {
                finish()
            }
        }
    }

    fun validarCampoContraseña(pass: String, passRep: String) : Boolean {

        if (pass.equals(passRep)) {
            return true
        } else {
            return false
        }

    }

}
