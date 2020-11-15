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
import javax.security.auth.callback.Callback
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
            inputRecuperarSenha.error = "Informe um email válido!"
            inputRecuperarSenha.requestFocus()
        } else {
            enviarEmailRecuperacao()
        }

    }

    fun enviarEmailRecuperacao() {
        val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)

        val email = EmailRecuperacaoSenha(
            inputRecuperarSenha.text.toString()
        )

        val resultado = requests.getEmailRecuperacao(email)

        //resultado.enqueue(object :Callback<Void>{

       // }


    }

}




