package com.orion.zenite.telas.autenticacao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.orion.zenite.R
import com.orion.zenite.telas.fiscal.MainFiscal
import com.orion.zenite.telas.motorista.MainMotorista
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun authLogin(component: View) {
        if (inputEmail.text.isBlank()) {

            inputEmail.error = "Informe seu email"
            inputEmail.requestFocus()
        } else if(inputSenha.text.isBlank()){
            inputSenha.error = "Informe sua senha";
            inputEmail.requestFocus()
        }else {
            val fiscal = Intent(this@Login, MainFiscal::class.java)
            val motorista = Intent(this@Login, MainMotorista::class.java)

            //Autenticação de Login
            if(inputEmail.text.toString().toLowerCase().equals("m")) {
                startActivity(motorista)
            } else {
                startActivity(fiscal)
            }
        }
    }

    fun irRecuperarSenha(view: View) {
        val recuperarSenhaActivity = Intent(this@Login, RecuperarSenha::class.java)
        startActivity(recuperarSenhaActivity)
    }
}