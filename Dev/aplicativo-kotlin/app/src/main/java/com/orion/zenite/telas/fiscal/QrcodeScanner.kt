package com.orion.zenite.telas.fiscal

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.budiyev.android.codescanner.AutoFocusMode
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.budiyev.android.codescanner.ScanMode
import com.google.gson.Gson
import com.google.zxing.BarcodeFormat
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.model.IniciarViagem
import com.orion.zenite.model.QrcodeMotorista
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar
import kotlinx.android.synthetic.main.activity_qrcode_scanner.loading_view
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QrcodeScanner : AppCompatActivity() {

    // tutorial do scanner
    // https://androiddvlpr.com/zxing-android-example/

    private lateinit var codeScanner: CodeScanner
    private lateinit var qrcodeMotorista: QrcodeMotorista

    val loading = MutableLiveData<Boolean>()
    val respostaRequisicao = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcode_scanner)

        // se clicar no botao de voltar fecha tela atual
        topAppBar.setNavigationOnClickListener {
            this.finish()
        }

        // mostra alerta para dar permissao de uso da camera ao app ou inicializa o escaner
        if (ContextCompat.checkSelfPermission(
                this@QrcodeScanner,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_DENIED
        ) {
            ActivityCompat.requestPermissions(
                this@QrcodeScanner,
                arrayOf(Manifest.permission.CAMERA),
                123
            )
        } else {
            escanear()
        }

        // ao realizar a ação mostra loader
        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
            }
        })

        // se a ação for bem sucedida leva para proxima tela
        // se não mostra toast com mensagem de erro
        respostaRequisicao.observe(this, Observer { result ->
            result?.let {
                if (it) {
                    if (qrcodeMotorista.iniciarViagem) {
                        Toast.makeText(
                            this,
                            "VIAGEM INICIADA!",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this, MainFiscal::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(
                            this,
                            "VIAGEM FINALIZADA!",
                            Toast.LENGTH_LONG
                        ).show()
                        val intent = Intent(this, QtdPassageiros::class.java)
                        intent.putExtra("idViagem", qrcodeMotorista.idViagem)
                        startActivity(intent)
                    }
                } else {
                    Toast.makeText(
                        this,
                        "Ocorreu um erro ao realizar a ação, tente novamente",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    private fun escanear() {
        // PARAMETROS DO ESCANEADOR
        val scannerView: CodeScannerView = findViewById(R.id.scanner_view)
        codeScanner = CodeScanner(this, scannerView)
        codeScanner.camera = CodeScanner.CAMERA_BACK
        codeScanner.formats = listOf(BarcodeFormat.QR_CODE)
        codeScanner.autoFocusMode = AutoFocusMode.SAFE // or CONTINUOUS
        codeScanner.scanMode = ScanMode.SINGLE // or CONTINUOUS or PREVIEW
        codeScanner.isAutoFocusEnabled = true // Whether to enable auto focus or not
        codeScanner.isFlashEnabled = false // Whether to enable flash or not

        // AO LER QRCODE CORRETAMENTE
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                try {
                    // CONVERTE DADOS DO QRCODE PARA CLASSE qrcodeData
                    qrcodeMotorista = Gson().fromJson(it.text, QrcodeMotorista::class.java)

                    // CAIXA DE DIALOGO PARA OBTER CONFIRMAÇÃO DA AÇÃO
                    alertConfirmarAcao(if (qrcodeMotorista.iniciarViagem) "Iniciar Viagem" else "Finalizar Viagem");
                } catch (e: Throwable) {
                    Toast.makeText(
                        this, "Ocorreu um erro ao escanear este QR Code, tente novamente.",
                        Toast.LENGTH_LONG
                    ).show()

                }
            }
        }
        // SE OCORRER ERRO AO ESCANEAR QRCODE
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            runOnUiThread {
                Toast.makeText(
                    this, "Erro na inicialização da camera: ${it.message}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        scannerView.setOnClickListener {
            codeScanner.startPreview()
        }
    }


    private val confirmarAcao = { dialog: DialogInterface, which: Int ->
        loading.value = true
        if (qrcodeMotorista.iniciarViagem) {
            iniciarViagem()
        } else {
            finalizarViagem()
        }
    }

    private val cancelarAcao = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext, "A ação não foi realizada.", Toast.LENGTH_SHORT).show()
    }

    private fun iniciarViagem() {
        // TODO REMOVER DADOS ESTATICOS => IDFISCAL E JWT TOKEN
        val token =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"
        val fiscalId = 4

        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
        val viagem = IniciarViagem(fiscalId, qrcodeMotorista.id)

        val resultado = service.iniciarViagem(viagem, token)

        resultado.enqueue(object : Callback<Void> {
            override fun onFailure(call: Call<Void>, t: Throwable) {
                respostaRequisicao.value = false
                loading.value = false
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                respostaRequisicao.value = true
                loading.value = false
            }
        })
    }

    private fun finalizarViagem() {
        // TODO REMOVER DADOS ESTATICOS => IDFISCAL E JWT TOKEN
        val token =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"
        val fiscalId = 4

        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)

        if (qrcodeMotorista.idViagem != null) {
            val resultado = service.finalizarViagem(qrcodeMotorista.idViagem!!, fiscalId, token)

            resultado.enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    respostaRequisicao.value = false
                    loading.value = false
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    respostaRequisicao.value = true
                    loading.value = false
                }
            })
        } else {
            respostaRequisicao.value = false
            loading.value = false
        }
    }

    fun alertConfirmarAcao(titleAlert: String) {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle(titleAlert)
            setMessage("Essa ação não pode ser desfeita")
            setPositiveButton("OK", DialogInterface.OnClickListener(function = confirmarAcao))
            setNegativeButton(android.R.string.no, cancelarAcao)
            show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 123) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Camera permission granted", Toast.LENGTH_LONG).show()
                escanear()
            } else {
                Toast.makeText(this, "Camera permission denied", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        if (::codeScanner.isInitialized) {
            codeScanner?.startPreview()
        }
    }

    override fun onPause() {
        if (::codeScanner.isInitialized) {
            codeScanner?.releaseResources()
        }
        super.onPause()
    }

}