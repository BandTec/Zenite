package com.orion.zenite.telas.fiscal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.orion.zenite.R
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_cronograma_linha.*

class AlterarIntervalo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alterar_intervalo)

        val nomeLinha = intent.extras?.getString("nomeLinha")
        topAppBar.title = nomeLinha

        val token = AppPreferencias.token
        val id = AppPreferencias.id

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }
    }

    fun alterarIntervalo(component:View) {
        //alterarIntervalo
        this.finish()
    }
}