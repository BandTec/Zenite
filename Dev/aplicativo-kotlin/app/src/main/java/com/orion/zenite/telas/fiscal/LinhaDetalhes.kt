package com.orion.zenite.telas.fiscal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.orion.zenite.R
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar
import kotlinx.android.synthetic.main.activity_linha.*

class LinhaDetalhes : AppCompatActivity() {
    var nomeLinha: String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linha)

        nomeLinha = intent.extras?.getString("linha").toString()
        linha.text = nomeLinha

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }
    }

    fun irCronograma(view: View){
        val intent = Intent(this, LinhaCronograma::class.java)
        intent.putExtra("nomeLinha", nomeLinha)
        startActivity(intent)
    }

    fun irMotorista(view: View){
        val intent = Intent(this, LinhaMotorista::class.java)
        intent.putExtra("nomeLinha", nomeLinha)
        startActivity(intent)
    }
}