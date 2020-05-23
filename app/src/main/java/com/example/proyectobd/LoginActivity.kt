package com.example.proyectobd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.proyectobd.clases.Producto
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() , View.OnClickListener {

    lateinit var usuario: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        btnRegistrar.setOnClickListener(this)
        btnIngresar.setOnClickListener(this)

        val obj = Producto(1, true, "Hoy", "1234", "12345", "Producto de prueba",
        10.0f, 3, 1, 1, 1, 1)

        obj.mostrar(this)

        obj.esComprado( "Se compro")
        obj.esModificado("Juan","Se modifico")
        obj.mostrar(this)

    }

    override fun onClick(v: View) {

        when(v.id) {
            R.id.btnIngresar -> {
                val db = DatabaseHelper(this)
                val correo = text_correo.text.toString()
                val pass = text_pass.text.toString()

                if(db.validarUsuario(correo, pass)) {
                    Toast.makeText(this, "Usuario validado", Toast.LENGTH_LONG).show()
                    usuario = db.usuario
                    val otrointent: Intent = Intent(this, RecyclerActivity::class.java)
                    val bundle = Bundle()
                    bundle.putSerializable("obj", usuario)
                    otrointent.putExtras(bundle)
                    text_pass.setText("")
                    this.startActivity(otrointent)
                } else {
                    Toast.makeText(this, "Error al ingresar", Toast.LENGTH_LONG).show()
                    text_pass.setText("")
                }

            }
            R.id.btnRegistrar -> {
                val intent: Intent = Intent(this, SiginActivity::class.java)
                startActivity(intent)
            }
        }

    }

    /*fun onClick(v: View?) {
        if(btnIngresar.isClickable) {

            usuario = Usuario(text_correo.text.toString(), text_pass.text.toString())

            if (validarUsuario(usuario)) {
                val intent: Intent = Intent(this, RecyclerActivity::class.java)
                val bundle = Bundle()
                bundle.putSerializable("obj", usuario)
                intent.putExtras(bundle)

            } else {
                Toast.makeText(this, "Error al ingresar", Toast.LENGTH_LONG)
            }


        }
    }*/

    /*fun validarUsuario(user: Usuario) : Boolean {

        val db = DatabaseHelper(this)
        val resultado = db.obtenerUsuario(user.correoElectronico, user.contraseña)
        return true

    }*/

}
