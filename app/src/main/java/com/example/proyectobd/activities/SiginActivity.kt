package com.example.proyectobd.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.proyectobd.R
import com.example.proyectobd.clases.DatabaseHelper
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
                val db = DatabaseHelper(this)
                val correo = campo_correo.text.toString()
                val contraseña = campo_contraseña.text.toString()
                if(db.registrarUsuario(correo, contraseña)) {
                    Toast.makeText(this, "Guardado Correctamente ", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this, "Error ", Toast.LENGTH_LONG).show()
                }
            }
            R.id.btn2 -> {
                finish()
            }
        }
    }

}
