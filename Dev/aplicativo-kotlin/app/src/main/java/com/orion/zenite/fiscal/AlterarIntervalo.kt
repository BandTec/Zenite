package com.orion.zenite.fiscal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.orion.zenite.R
import com.orion.zenite.autenticacao.RecuperarSenha
import kotlinx.android.synthetic.main.activity_cronograma_linha.*

class AlterarIntervalo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alterar_intervalo)

        val nomeLinha = intent.extras?.getString("nomeLinha")
        topAppBar.title = nomeLinha

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }
    }

    fun alterarIntervalo(component:View) {
        //alterarIntervalo
        this.finish()
    }
}