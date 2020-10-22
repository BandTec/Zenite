package com.orion.zenite.telas.autenticacao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.autenticacao.LoginApi
import com.orion.zenite.model.Token
import com.orion.zenite.model.Usuario
import com.orion.zenite.telas.fiscal.MainFiscal
import com.orion.zenite.telas.motorista.MainMotorista
import kotlinx.android.synthetic.main.activity_login.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        btn_entrar.setOnClickListener() {
            //validations

            val email = input_email.text.toString().trim()
            val senha = input_senha.text.toString().trim()

            //verificação dos campos
            if (email.isBlank()) {
                input_email.error = "Informe seu email"
                input_email.requestFocus()
                return@setOnClickListener

            } else if (senha.isBlank()) {
                input_senha.error = "Informe sua senha";
                input_email.requestFocus()
                return@setOnClickListener
            }

            val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)
            //val resultado = requests.getUsuario(idUser, token)

            val usuario = Usuario(
                input_senha.text.toString(),
                input_email.text.toString()
            )

            val fiscalAtv = Intent(this@Login, MainFiscal::class.java)
            val motoristaAtv = Intent(this@Login, MainMotorista::class.java)

            val userLoginRequest = requests.postloginRequest(usuario)

            userLoginRequest.enqueue(object : Callback<Token> {
                override fun onFailure(call: Call<Token>, t: Throwable) {
                    Toast.makeText(baseContext, "Deu Ruim mermão  ${t.message}", Toast.LENGTH_SHORT).show()
                }

                override fun onResponse(call: Call<Token>, response: Response<Token>) {
                    //TODO 1 - condição se response não der error
                    //TODO 2 - salvar  dados do usuário logado
                    //TODO 3 - condição para redirecionar p/ tela
                    // de acordo com nível de acesso do Usuario
                    //tokenStr = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"


                    val tokenStr = "${response.body()?.message.toString()}"
                    Toast.makeText(baseContext, "Token  ${tokenStr}", Toast.LENGTH_SHORT).show()



                    //Autenticação de Login
                    /*if (email.toLowerCase().equals("m")) {startActivity(motorista)
                    } else {startActivity(fiscal)}*/



                    //motoristaAtv.flags = Intent.FLAG_ACTIVITY_NEW_TASK  or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    //startActivity(motoristaAtv)

                }
            })
        }


        fun irRecuperarSenha(view: View) {
            val recuperarSenhaActivity = Intent(this@Login, RecuperarSenha::class.java)
            startActivity(recuperarSenhaActivity)
        }
    }
}
