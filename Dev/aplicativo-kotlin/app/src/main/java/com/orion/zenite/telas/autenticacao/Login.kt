package com.orion.zenite.telas.autenticacao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.autenticacao.LoginApi
import com.orion.zenite.model.Conta
import com.orion.zenite.model.Token
import com.orion.zenite.model.Usuario
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    val tokenStr = MutableLiveData<Token>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun verificarDados(view: View) {
        //validations
        val email = input_email.text.toString().trim()
        val senha = input_senha.text.toString().trim()

        if (email.isBlank()) {
            input_email.error = "Informe seu email"
            input_email.requestFocus()

        } else if (senha.isBlank()) {
            input_senha.error = "Informe sua senha";
            input_email.requestFocus()

        }

        val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)
        val usuario = Usuario(
            input_senha.text.toString(),
            input_email.text.toString()
        )

        val LoginRequest = requests.postloginRequest(usuario)

        LoginRequest.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                Toast.makeText(baseContext, "Deu Ruim mermão  ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    val token = response.body()?.message.toString()
                    // Toast.makeText(baseContext, "Token  ${token}", Toast.LENGTH_SHORT).show()
                    Toast.makeText(baseContext, "Login Efetuado", Toast.LENGTH_SHORT).show()
                    tokenStr.value = response.body()

                } else {
                    Toast.makeText(baseContext, "Falha no login", Toast.LENGTH_SHORT).show()
                }
            }


        })
    }


    fun buscarDadosConta() {
        val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)
        val tokenValue = Token(
            tokenStr,
            true
        )

        val contaRequest = requests.getDadosConta(tokenValue)

        val resultado = requests.getDadosConta(token)

        contaRequest.enqueue(object : Callback<Conta> {
            override fun onFailure(call: Call<Conta>, t: Throwable) {
                Toast.makeText(baseContext, "Erro  ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<Conta>, response: Response<Conta>) {

            }

            /*if (email.toLowerCase().equals("m")) {startActivity(motorista)
            } else {startActivity(fiscal)}*/


            //motoristaAtv.flags = Intent.FLAG_ACTIVITY_NEW_TASK  or Intent.FLAG_ACTIVITY_CLEAR_TASK
            //startActivity(motoristaAtv)
        }


            fun irRecuperarSenha(view: View) {
                val recuperarSenhaActivity = Intent(this@Login, RecuperarSenha::class.java)
                startActivity(recuperarSenhaActivity)
            }
    }
