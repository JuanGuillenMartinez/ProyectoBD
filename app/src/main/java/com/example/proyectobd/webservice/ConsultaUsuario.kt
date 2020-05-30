package com.example.proyectobd.webservice

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.preference.PreferenceManager
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.proyectobd.activities.RecyclerActivity
import com.example.proyectobd.clases.Preference
import com.example.proyectobd.clases.Usuario
import org.json.JSONObject

class ConsultaUsuario (context: Context) {

    val context = context
    val urlValidarUsuario = "https://mysterious-woodland-17155.herokuapp.com/api_rest/Validar_usuario_GET.php"
    val urlValidarPassword = "https://mysterious-woodland-17155.herokuapp.com/api_rest/Validar_contrase%C3%B1a_POST.php"

    fun validarUsuario(username: String, password: String) {

        AndroidNetworking.get(urlValidarUsuario+"?username=$username")
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object: JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val resultado: JSONObject = response.getJSONObject("data")
                    val nombre: String = resultado.getString("username")
                    if(nombre.equals(username)) {
                        validarPassword(nombre, password)
                    } else if (nombre.equals("indefinido")){
                        Toast.makeText(context, "El usuario ingresado no existe", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(context, "Error " + anError.errorDetail, Toast.LENGTH_LONG ).show()
                }

            })

    }

    fun validarPassword(username: String, password: String) {

        AndroidNetworking.post(urlValidarPassword)
            .addBodyParameter("username", username)
            .addBodyParameter("password", password)
            .setPriority(Priority.MEDIUM)
            .build()
            .getAsJSONObject(object: JSONObjectRequestListener {
                override fun onResponse(response: JSONObject) {
                    val resultado: JSONObject = response.getJSONObject("data")
                    val nombreUsuario: String = resultado.getString("username").toString()
                    if(nombreUsuario.equals(username)) {

                        val id: Int = resultado.getString("id").toInt()
                        val firstname: String = resultado.getString("first_name")
                        val lastname: String = resultado.getString("last_name")
                        val correo: String = resultado.getString("email")
                        val isSuperuser: String = resultado.getString("is_superuser")
                        val createdAt: String = resultado.getString("date_joined")
                        val lastLogin: String = resultado.getString("last_login")
                        val isStaff: String = resultado.getString("is_staff")
                        val isActive: String = resultado.getString("is_active")

                        val intent = Intent(context, RecyclerActivity::class.java)
                        val usuario = Usuario(id, nombreUsuario, correo, firstname, lastname, isSuperuser, createdAt, isStaff, isActive, lastLogin)
                        val bundle: Bundle = Bundle()
                        bundle.putSerializable("obj", usuario)
                        intent.putExtras(bundle)
                        val pref = Preference(context)
                        pref.setUsername(nombreUsuario)
                        pref.setId(id)
                        context.startActivity(intent)

                    } else if (nombreUsuario.equals("indefinido")){
                        Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(context, "Error " + anError.errorDetail, Toast.LENGTH_LONG ).show()
                }

            })

    }

}