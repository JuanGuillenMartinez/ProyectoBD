package com.example.proyectobd.clases

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper (context: Context) :
    SQLiteOpenHelper( context,
        nomDB, null, 1) {

    lateinit var usuario: Usuario

    companion object {

        val nomDB = "proyecto.db"
        val nomTabla = "productos"
        val col1 = "id_producto"
        val col2 = "nombre_producto"
        val col3 = "precio"
        val col4 = "existencia"

    }

    override fun onCreate(db: SQLiteDatabase) {
            db.execSQL( "CREATE TABLE $nomTabla (id_producto INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "nombre_producto TEXT, precio REAL, existencia INTEGER)" )
            db.execSQL("CREATE TABLE usuarios (id_usuario INTEGER PRIMARY KEY AUTOINCREMENT, " +
                            "correo_electronico TEXT, contraseña TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        db.execSQL("DROP TABLE IF EXISTS " + nomTabla)
        onCreate(db)

    }

    fun agregar(nombre: String, precio: String, existencia: String ) {
        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(col2, nombre)
        cv.put(col3, precio)
        cv.put(col4, existencia)
        db.insert(nomTabla, null, cv)
    }

    fun actualizar(id: String, nombre: String, precio: String, existencia: String ) : Boolean {

        val db = this.writableDatabase
        val cv = ContentValues()
        cv.put(col1, id)
        cv.put(col2, nombre)
        cv.put(col3, precio)
        cv.put(col4, existencia)
        db.update(nomTabla, cv, "id_producto = ?", arrayOf(id))
        return true

    }

    fun borrar(id: String) : Int {
        val db = this.writableDatabase
        return db.delete(nomTabla, "id_producto = ?", arrayOf(id))
    }

    fun registrarUsuario(correo: String, pass: String) : Boolean {

        try {
            val db = this.writableDatabase
            val cv = ContentValues()
            cv.put("correo_electronico", correo)
            cv.put("contraseña", pass)
            db.insert("usuarios", null, cv)
            return true
        } catch (e: Exception) {
            e.printStackTrace()
            return false
        }

    }

    fun validarUsuario(correo: String, pass: String) : Boolean {

        val db = this.writableDatabase
        val args = arrayOf(correo, pass)
        val resultado = db.rawQuery("SELECT * FROM usuarios WHERE correo_electronico=? AND contraseña = ?" , args)

        while (resultado.moveToNext()) {
            val correo = resultado.getString(1)
            val pass = resultado.getString(2)
            usuario = Usuario(correo, pass)
            return  true
        }
        return false
    }

    val obtenerDatos : Cursor
        get() {
            val db = this.writableDatabase
            val resultado = db.rawQuery("SELECT * FROM " + nomTabla, null)
            return resultado
        }

}
