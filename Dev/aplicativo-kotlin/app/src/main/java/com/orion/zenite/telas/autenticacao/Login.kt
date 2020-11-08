package com.orion.zenite.telas.autenticacao

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.autenticacao.LoginApi
import com.orion.zenite.model.UserZenite
import com.orion.zenite.model.Token
import com.orion.zenite.model.Usuario
import com.orion.zenite.telas.fiscal.MainFiscal
import com.orion.zenite.telas.motorista.MainMotorista
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    val loading = MutableLiveData<Boolean>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val token = AppPreferencias.token
        val id = AppPreferencias.id
        val nivel = AppPreferencias.nivel

        if (nivel != 0) {
            if (nivel == 4) {
                val fiscal = Intent(this@Login, MainFiscal::class.java)
                startActivity(fiscal)
            } else {
                val motorista = Intent(this@Login, MainMotorista::class.java)
                startActivity(motorista)
            }
        }

        loading.value = false
        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loadinger.visibility = if (it) View.VISIBLE else View.GONE

            }
        })
    }

    fun verificarDados(view: View) {
        //validations
        val email = input_email.text.toString().trim()
        val senha = input_senha.text.toString().trim()

        if (email.isBlank()) {
            input_email.error = getString(R.string.login_error_campo)
            input_email.requestFocus()

        } else if (senha.isBlank()) {
            input_senha.error = getString(R.string.senha_error_campo);
            input_email.requestFocus()

        } else {
            logar()
        }
    }

    fun logar() {
        val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)
        val usuario = Usuario(
            input_senha.text.toString(),
            input_email.text.toString()
        )

        val LoginRequest = requests.postloginRequest(usuario)
        loading.value = true;

        LoginRequest.enqueue(object : Callback<Token> {
            override fun onFailure(call: Call<Token>, t: Throwable) {
                loading.value = false
                Toast.makeText(
                    baseContext,
                    getString(R.string.erro_autentificacao),
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                if (response.isSuccessful) {
                    val token = response.body()?.message.toString()
                    buscarDadosConta(token)
                } else {
                    Toast.makeText(
                        baseContext,
                        getString(R.string.erro_autentificacao),
                        Toast.LENGTH_SHORT
                    ).show()
                    loading.value = false
                }
            }
        })
    }


    fun buscarDadosConta(token: String) {
        val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)

        val resultado = requests.getDadosConta(token)

        resultado.enqueue(object : Callback<UserZenite> {
            override fun onFailure(call: Call<UserZenite>, t: Throwable) {
                loading.value = false
                Toast.makeText(
                    baseContext,
                    getString(R.string.erro_autentificacao),
                    Toast.LENGTH_SHORT
                ).show()
                println("deu ruim " + t.message)
            }

            override fun onResponse(call: Call<UserZenite>, response: Response<UserZenite>) {
                val motorista = Intent(this@Login, MainMotorista::class.java)
                val fiscal = Intent(this@Login, MainFiscal::class.java)
                val usuarioLogado = response.body()
                println("status code" + response.code())
                if (usuarioLogado?.conta?.nivel?.id !== null) {

                    AppPreferencias.token = token;
                    AppPreferencias.id = usuarioLogado.id


                    if (usuarioLogado.conta.nivel.id == 4) {
                        AppPreferencias.nivel = 4

                        loading.value = false
                        startActivity(fiscal)
                    } else if (usuarioLogado.conta.nivel.id == 5) {
                        AppPreferencias.nivel = 5

                        loading.value = false
                        startActivity(motorista)
                    } else {
                        loading.value = false
                        Toast.makeText(
                            baseContext,
                            getString(R.string.sem_acesso),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    loading.value = false
                    Toast.makeText(
                        baseContext,
                        getString(R.string.erro_autentificacao),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        })
    }


    fun irRecuperarSenha(view: View) {
        val recuperarSenhaActivity = Intent(this@Login, RecuperarSenha::class.java)
        startActivity(recuperarSenhaActivity)
    }
}
