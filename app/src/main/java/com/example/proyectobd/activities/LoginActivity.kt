package com.example.proyectobd.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.cloudinary.android.MediaManager
import com.example.proyectobd.R
import com.example.proyectobd.clases.*
import com.example.proyectobd.webservice.ConsultaUsuario
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnRegistrar.setOnClickListener(this)
        btnIngresar.setOnClickListener(this)
        MediaManager.init(this, Galeria.getConfiguracion())
    }

    override fun onClick(v: View) {

        when(v.id) {
            R.id.btnIngresar -> {
                val correo = text_correo.text.toString().trim()
                val pass: String = Hasher.hash(text_pass.text.toString())
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
