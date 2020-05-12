package com.example.proyectobd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InformationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_information)

        val objeto = intent.extras
        val producto: Productos = objeto?.getSerializable("obj") as Productos

    }

}
