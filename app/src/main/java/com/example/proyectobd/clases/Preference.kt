package com.example.proyectobd.clases

import android.content.Context

class Preference (context: Context){

    val preferenceName: String = "user_preferences"
    val usernamePreference: String = "username"
    val idUserPreference: String = "id_username"

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

    fun setId(id: Int) {

        val editor = preference.edit()
        editor.putInt(idUserPreference, id)
        editor.apply()

    }

    fun getId() : Int {

        val id = preference.getInt(idUserPreference, 0)

        return  id
    }

}