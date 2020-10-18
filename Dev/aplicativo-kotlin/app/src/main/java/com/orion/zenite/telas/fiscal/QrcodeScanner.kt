package com.orion.zenite.telas.fiscal

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.orion.zenite.R
import com.orion.zenite.model.Qrcode
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar

class QrcodeScanner : AppCompatActivity() {

    private lateinit var codeScanner: CodeScanner
    // https://androiddvlpr.com/zxing-android-example/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_scanner)

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }

        if (ContextCompat.checkSelfPermission(this@QrcodeScanner, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
            ActivityCompat.requestPermissions(this@QrcodeScanner, arrayOf(Manifest.permission.CAMERA), 123)
        } else {
            startScanning()
        }
    }

    private fun startScanning() {
        // Parameters (default values)
        val scannerView: CodeScannerView = findViewById(R.id.scanner_view)
        codeScanner = CodeScanner(this, scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK // or CAMERA_FRONT or specific camera id
        codeScanner.formats = listOf(BarcodeFormat.QR_CODE) // list of type BarcodeFormat,
        // ex. listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // Callbacks
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {

                Toast.makeText(this, it.text, Toast.LENGTH_LONG).show()
                // https://www.baeldung.com/kotlin-json-convert-data-class
                var gson = Gson()
                var jsonString = gson.fromJson(it.text, Qrcode::class.java)


                if (jsonString != null)
                {
                    Toast.makeText(this, jsonString.id, Toast.LENGTH_LONG).show()
//                    var id = parsedResult.identifier;
//                    Toast.makeText(this, id, Toast.LENGTH_LONG).show()
//
//                    // TODO: adicionar dialog se deseja iniciar viagem
//                    // TODO: ADICIONAR LOGICA PARA PEGAR DADOS DO QRCODE PARA ENVIAR PARA A API
//
//                    val intent = Intent(this, QtdPassageiros::class.java)
//                    startActivity(intent)

                }else {
                    Toast.makeText(this, "Ocorreu um erro, tente novamente", Toast.LENGTH_LONG).show()
                }
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(this, "Camera initialization error: ${it.message}",
                    Toast.LENGTH_LONG).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show()
                startScanning()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if(::codeScanner.isInitialized) {
            codeScanner?.startPreview()
        }
    }

    override fun onPause() {
        if(::codeScanner.isInitialized) {
            codeScanner?.releaseResources()
        }
        super.onPause()
    }

}