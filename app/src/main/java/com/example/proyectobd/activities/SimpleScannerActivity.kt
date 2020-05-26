package com.example.proyectobd.activities

import android.app.Activity
import android.app.Instrumentation
import android.content.Intent
import android.content.pm.PackageManager
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import java.lang.Exception
import java.util.jar.Manifest

class SimpleScannerActivity : AppCompatActivity(), ZBarScannerView.ResultHandler{

    lateinit var ZBarScanner: ZBarScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val permiso = arrayOf(android.Manifest.permission.CAMERA)

        if(ContextCompat.checkSelfPermission(this, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permiso, 0  )
        } else {
            ZBarScanner = ZBarScannerView(this)
            setContentView(ZBarScanner)
        }
    }

    override fun onResume() {
        super.onResume()
        ZBarScanner.setResultHandler(this)
        ZBarScanner.startCamera()
    }

    override fun onPause() {
        super.onPause()
        ZBarScanner.stopCamera()
    }

    override fun handleResult(rawResult: Result) {

        val code: String = rawResult.contents
        val format: String = rawResult.barcodeFormat.name
        val result: String = code

        try {

            val notificacion: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val sonido: Ringtone = RingtoneManager.getRingtone(applicationContext, notificacion)
            sonido.play()

            val intent: Intent = Intent()
            intent.putExtra("codigo", code)
            setResult(Activity.RESULT_OK, intent)

            finish()

        } catch(e: Exception) {
            Log.e("ScannerLog", e.localizedMessage)
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show()

    }

}