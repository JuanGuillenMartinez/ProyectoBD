package com.example.proyectobd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
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
                val intent: Intent = Intent(this, RecyclerActivity::class.java)
                startActivity(intent)
            }
        }
    }

}
