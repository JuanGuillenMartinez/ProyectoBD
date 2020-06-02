package com.example.proyectobd.clases

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import androidx.core.graphics.drawable.toDrawable
import com.cloudinary.android.MediaManager
import java.io.ByteArrayOutputStream
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

        fun guardarFoto(nombre: String, image: Bitmap) {
            val stream = ByteArrayOutputStream()
            image.compress(Bitmap.CompressFormat.JPEG, 50, stream)
            val foto = stream.toByteArray()
            val requestId: String = MediaManager.get()
                .upload(foto)
                .option("public_id", nombre)
                .option("folder", "Proyecto/Productos/")
                .dispatch();
            stream.close()
        }


        fun obtenerUrlFoto(carpeta: String, subcarpeta: String, nombre: String) : String {
            val url = MediaManager.get().url().generate("${carpeta}${subcarpeta}$nombre")
            return url
        }

    }

}