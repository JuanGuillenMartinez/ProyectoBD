package com.example.proyectobd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun onClick(v: View?) {
        if(btnIngresar.isClickable) {
            val intent: Intent = Intent(this, ProductosActivity::class.java)
            startActivity(intent)
        }
    }
    fun activar(v: View?) {
        if(btnRegistrar.isClickable) {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }


}
