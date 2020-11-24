package com.orion.zenite.telas.fiscal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.autenticacao.LoginApi
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.model.NovoIntervalo
import com.orion.zenite.model.QtdPassageiros
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_alterar_intervalo.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AlterarIntervalo : AppCompatActivity() {
    val loading = MutableLiveData<Boolean>()
    val respostaRequisicao = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alterar_intervalo)

        val nomeLinha = intent.extras?.getString("nomeLinha")
        topAppBar.title = nomeLinha

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }
    }

    fun alterarIntervalo(component: View) {
        val intervalo = input_novo_intervalo.text.toString().trim()

        if (intervalo.isBlank()) {
            input_email.error = getString(R.string.input_alterar_vazio)
            input_email.requestFocus()

        } else {
            alertConfirmarAcao()
        }
        //this.finish()
    }

    fun voltar(){
        this.finish();
    }

    fun alterar() {

        val intervalo = input_novo_intervalo.text.toString()
        val body = NovoIntervalo(intervalo)
        val token = AppPreferencias.token
        val idLinha = AppPreferencias.idLinha


        if (idLinha != null) {
            val requests: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
            val resultado = requests.alterarIntervaloViagem(idLinha, intervalo, token)

            resultado.enqueue(object : Callback<Void> {
                override fun onFailure(call: Call<Void>, t: Throwable) {
                    respostaRequisicao.value = false
                    loading.value = false
                    Toast.makeText(baseContext, getString(R.string.erro_alterar_intervalo), Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Void>, response: Response<Void>) {
                    respostaRequisicao.value = true
                    loading.value = false

                    voltar()
                }
            })
        } else {
            respostaRequisicao.value = false
            loading.value = false
        }
    }

    private val confirmarAcao = { dialog: DialogInterface, which: Int ->
        loading.value = true
        alterar()
    }

    private val cancelarAcao = { dialog: DialogInterface, which: Int ->
        Toast.makeText(applicationContext, getString(R.string.acao_erro), Toast.LENGTH_SHORT).show()
    }

    fun alertConfirmarAcao() {
        val builder = AlertDialog.Builder(this)
        with(builder)
        {
            setTitle(getString(R.string.alterar_intervalo))
            setMessage(getString(R.string.acao_nao_desfeita))
            setPositiveButton("OK", DialogInterface.OnClickListener(function = confirmarAcao))
            setNegativeButton(android.R.string.no, cancelarAcao)
            show()
        }
    }
}
