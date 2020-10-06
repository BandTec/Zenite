package com.orion.zenite.fiscal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.orion.zenite.R
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_fiscal.*

class Linha : AppCompatActivity() {
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
        val intent = Intent(this, CronogramaLinha::class.java)
        intent.putExtra("nomeLinha", nomeLinha)
        startActivity(intent)
    }

    fun irMotorista(view: View){
        Toast.makeText(this, "Você clicou em OLá", Toast.LENGTH_SHORT).show()
//        val intent = Intent(this, CronogramaLinha::class.java)
//        intent.putExtra("nomeLinha", nomeLinha)
//        startActivity(intent)
    }
}