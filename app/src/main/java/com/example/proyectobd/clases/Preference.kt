package com.example.proyectobd.clases

import android.content.Context

class Preference (context: Context){

    val preferenceName: String = "Preferences"
    val usernamePreference: String = "username"

    val preference = context.getSharedPreferences(preferenceName, Context.MODE_PRIVATE)

    fun getUsername() : String? {

        val username: String? = preference.getString(usernamePreference, "Indefinido")

        return username
    }

    fun setUsername(username: String) {

        val editor = preference.edit()
        editor.putString(usernamePreference, username)
        editor.apply()

    }

}