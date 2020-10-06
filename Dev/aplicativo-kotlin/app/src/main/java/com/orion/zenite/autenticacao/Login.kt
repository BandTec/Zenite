package com.orion.zenite.autenticacao

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import com.orion.zenite.R
import kotlinx.android.synthetic.main.activity_login.*

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

    }

    fun AuthLogin(component: View) {
        if (inputEmail.text.isBlank() || inputSenha.text.isBlank()) {

            inputEmail.error = "Informe os dados obrigatórios!"
            inputEmail.requestFocus()

        }
    }

    fun irRecuperarSenha(view: View) {
        val RecuperarSenhaActivity = Intent(this@Login, RecuperarSenha::class.java)

        startActivity(RecuperarSenhaActivity)
    }
}