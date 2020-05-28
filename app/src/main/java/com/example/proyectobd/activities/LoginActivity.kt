package com.example.proyectobd.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.proyectobd.R
import com.example.proyectobd.clases.Usuario
import com.example.proyectobd.clases.DatabaseHelper
import com.example.proyectobd.webservice.ConsultaUsuario
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnRegistrar.setOnClickListener(this)
        btnIngresar.setOnClickListener(this)
        val edit = findViewById<EditText>(R.id.text_pass)
        edit.setText("pbkdf2_sha256$180000\$LtX1pyRRz3RC\$PQySEomZX8jQVKXuE3pzTrme2YPp12n13JngCc0gpbI=")
    }

    override fun onClick(v: View) {

        when(v.id) {
            R.id.btnIngresar -> {
                val db = DatabaseHelper(this)
                val correo = text_correo.text.toString().trim()
                val pass = text_pass.text.toString()
                val consulta = ConsultaUsuario(this)
                consulta.validarUsuario(correo, pass)

            }
            R.id.btnRegistrar -> {
                val intent: Intent = Intent(this, SiginActivity::class.java)
                startActivity(intent)
            }
        }

    }

}
