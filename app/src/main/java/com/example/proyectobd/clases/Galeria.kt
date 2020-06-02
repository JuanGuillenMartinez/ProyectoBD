package com.example.proyectobd.clases

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import com.cloudinary.android.MediaManager
import java.io.File

class Galeria() {

    companion object {

        var foto: Uri? = null
        var peticionTomarFoto: Int = 20

        fun getConfiguracion() : MutableMap<String, String> {

            val config: MutableMap<String, String> = HashMap()
            config.put("cloud_name", "hwxl1vi1w")
            config.put("api_key", "677648181636663")
            config.put("api_secret", "h9Ru6GOfkI7cBw_0j82tlFpJo98")
            return config

        }

        fun tomarFoto(context: Context) : Uri? {
            val value = ContentValues()
            value.put(MediaStore.Images.Media.TITLE, "NuevaImagen")
            foto = context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, value)
            return foto
        }

    }

}