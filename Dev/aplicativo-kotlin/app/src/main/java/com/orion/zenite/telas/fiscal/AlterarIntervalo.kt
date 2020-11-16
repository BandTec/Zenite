package com.orion.zenite.telas.fiscal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.MutableLiveData
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.model.NovoIntervalo
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_alterar_intervalo.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class AlterarIntervalo : AppCompatActivity() {
    val loading = MutableLiveData<Boolean>()
    val respostaRequisicao = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alterar_intervalo)

        val nomeLinha = intent.extras?.getString("nomeLinha")
        topAppBar.title = nomeLinha

        val token = AppPreferencias.token
        val id = AppPreferencias.id

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }
    }

    fun alterarIntervalo(component:View) {

        val intervalo = input_novo_intervalo.text.toString().trim()

        if (intervalo.isBlank()) {
            input_novo_intervalo.error = "Informe o novo intervalo"
            input_novo_intervalo.requestFocus()

        } else {
            alterar()
        }
        //this.finish()
    }
        fun alterar() {
            val token =
                "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"
            val intervalo = input_novo_intervalo.text.toString().toInt()
            val body = NovoIntervalo(intervalo)

            val idLinha = intent.extras?.getInt("idLinha")


            if (idLinha != null) {
                val requests: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
                val resultado = requests.alterarIntervaloViagem(idLinha, body, token)

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

        //this.finish()
    }

