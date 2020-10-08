package com.orion.zenite.autenticacao

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.orion.zenite.R
import kotlinx.android.synthetic.main.activity_recuperar_senha.*


class RecuperarSenha : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_senha)
    }

    fun onBackPressed(view: View) {
        this.finish()
    }

    fun enviarEmailRecuperacao(component: View){
        //enviar email para recuperação
        if (inputRecuperarSenha.text.isBlank()){
            inputRecuperarSenha.error = "Informe um email válido!"
            inputRecuperarSenha.requestFocus()
        }else{
            Toast.makeText(this, "Você receberá um e-mail para recuperação de senha.", Toast.LENGTH_LONG).show()
            inputRecuperarSenha.setText("")
        }

    }
}