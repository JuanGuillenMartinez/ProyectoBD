package com.example.proyectobd.activities

import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import me.dm7.barcodescanner.zbar.Result
import me.dm7.barcodescanner.zbar.ZBarScannerView
import java.lang.Exception

class SimpleScannerActivity : AppCompatActivity(), ZBarScannerView.ResultHandler{

    lateinit var ZBarScanner: ZBarScannerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ZBarScanner = ZBarScannerView(this)
        setContentView(ZBarScanner)
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
        val result: String = "Contents = $code, Format = $format"

        try {
            val notificacion: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
            val sonido: Ringtone = RingtoneManager.getRingtone(applicationContext, notificacion)
            sonido.play()
        } catch(e: Exception) {
            Log.e("ScannerLog", e.localizedMessage)
        }

        Toast.makeText(this, result, Toast.LENGTH_LONG).show()

    }

}