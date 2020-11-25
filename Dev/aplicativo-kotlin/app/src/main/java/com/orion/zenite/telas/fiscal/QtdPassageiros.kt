package com.orion.zenite.telas.fiscal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.model.QtdPassageiros
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_main_fiscal.topAppBar
import kotlinx.android.synthetic.main.activity_qrcode_scanner.loading_view
import kotlinx.android.synthetic.main.activity_qtd_passageiros.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QtdPassageiros : AppCompatActivity() {

    val loading = MutableLiveData<Boolean>()
    val respostaRequisicao = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qtd_passageiros)

        // https://material.io/develop/android/components/app-bars-top
        topAppBar.setNavigationOnClickListener {
            this.finish()
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

                    Toast.makeText(
                        this,
                        getString(R.string.acao_sucesso),
                        Toast.LENGTH_LONG
                    ).show()

                    val intent = Intent(this, MainFiscal::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this,
                        getString(R.string.erro_viagem),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        })
    }

    fun salvarDados(view: View) {
        if (inputQuantidade.text.isBlank()) {
            inputQuantidade.error = getString(R.string.mensagem_passageiros_error)
            inputQuantidade.requestFocus()
        } else {
            alertConfirmarAcao()
        }

    }

    private val confirmarAcao = { dialog: DialogInterface, which: Int ->
        loading.value = true
        adicionar()
    }

    private val cancelarAcao = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext, getString(R.string.acao_erro), Toast.LENGTH_SHORT).show()
    }

    private fun adicionar() {
        //  REMOVER DADOS ESTATICOS => JWT TOKEN
       // val token =
      //      "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"
        val passageiros = inputQuantidade.text.toString().toInt()
        val body = QtdPassageiros(passageiros)
        val idViagem = intent.extras?.getInt("idViagem")
        val token = AppPreferencias.token

        if (idViagem != null) {
            val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
            val resultado = service.adicionarPassageiros(idViagem, body, token)

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

    fun alertConfirmarAcao() {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle(getString(R.string.passageiros_titulo))
            setMessage(getString(R.string.acao_nao_desfeita))
            setPositiveButton("OK", DialogInterface.OnClickListener(function = confirmarAcao))
            setNegativeButton(android.R.string.no, cancelarAcao)
            show()
        }
    }
}