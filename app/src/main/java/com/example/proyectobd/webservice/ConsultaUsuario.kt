package com.example.proyectobd.webservice

import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.JSONObjectRequestListener
import com.example.proyectobd.activities.RecyclerActivity
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
                    val nombre: String = resultado.getString("username").toString()
                    if(nombre.equals(username)) {
                        val intent = Intent(context, RecyclerActivity::class.java)
                        context.startActivity(intent)
                    } else if (nombre.equals("indefinido")){
                        Toast.makeText(context, "Contraseña incorrecta", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onError(anError: ANError) {
                    Toast.makeText(context, "Error " + anError.errorDetail, Toast.LENGTH_LONG ).show()
                }

            })

    }

}