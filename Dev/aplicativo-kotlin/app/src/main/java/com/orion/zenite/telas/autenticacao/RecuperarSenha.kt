package com.orion.zenite.telas.autenticacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.autenticacao.LoginApi
import com.orion.zenite.model.EmailRecuperacaoSenha
import kotlinx.android.synthetic.main.activity_recuperar_senha.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback
import java.lang.Void as Void



class RecuperarSenha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_senha)
    }

    fun onBackPressed(view: View) {
        this.finish()
    }

    fun verificarDados(component: View) {
        //enviar email para recuperação

        if (inputRecuperarSenha.text.isBlank()) {
            inputRecuperarSenha.error = getString(R.string.erro_informar_email)
            inputRecuperarSenha.requestFocus()
        } else {
            enviarEmailRecuperacao()
        }

    }

    fun enviarEmailRecuperacao() {
        val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)

        val email = inputRecuperarSenha.text.toString()

        val resultado = requests.getEmailRecuperacao(email)

        resultado.enqueue(object : Callback<Void>{
            override fun onFailure(call: Call<Void>, t: Throwable) {
                Toast.makeText(applicationContext, "Falha ao enviar e-mail de recuperação, tente novamente!", Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                print("Resposta: " + response.code())
            }
        })


    }

}




