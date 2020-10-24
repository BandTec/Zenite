package com.orion.zenite.telas.autenticacao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.autenticacao.LoginApi
import com.orion.zenite.model.Conta
import com.orion.zenite.model.Fiscal
import com.orion.zenite.model.Token
import com.orion.zenite.model.Usuario
import com.orion.zenite.telas.fiscal.MainFiscal
import com.orion.zenite.telas.motorista.MainMotorista
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {
    val tokenStr = MutableLiveData<Token>()
    val loading = MutableLiveData<Boolean>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


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
        loading.value = true;
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
//                    tokenStr.value = response.body()
                    buscarDadosConta(token)
                } else {
                    Toast.makeText(baseContext, "Falha no login", Toast.LENGTH_SHORT).show()
                }
            }


        })
    }


    fun buscarDadosConta(token: String) {
        val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)

        val resultado = requests.getDadosConta(token)

        resultado.enqueue(object : Callback<Fiscal> {
            override fun onFailure(call: Call<Fiscal>, t: Throwable) {
                Toast.makeText(baseContext, "Erro  ${t.message}", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<Fiscal>, response: Response<Fiscal>) {
                val motorista = Intent(this@Login, MainMotorista::class.java)
                val fiscal = Intent(this@Login, MainFiscal::class.java)
                Toast.makeText(baseContext, "hello", Toast.LENGTH_SHORT).show()
                val fiscalObj = response.body()

                if (fiscalObj?.conta?.nivel?.id !== null) {

                    if(fiscalObj.conta.nivel.id == 4){
                        loading.value = false
                        startActivity(fiscal)

                    }
                    if(fiscalObj.conta.nivel.id == 5) {
                        loading.value = false
                        startActivity(motorista)

                    }
                } else {
                    Toast.makeText(baseContext, "Deu Ruim mermão  ${response.code()}", Toast.LENGTH_SHORT)
                        .show()
                }
            }

        })
    }


    fun irRecuperarSenha(view: View) {
        val recuperarSenhaActivity = Intent(this@Login, RecuperarSenha::class.java)
        startActivity(recuperarSenhaActivity)
    }
}
