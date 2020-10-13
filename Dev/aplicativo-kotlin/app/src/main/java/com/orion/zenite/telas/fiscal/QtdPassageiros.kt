package com.orion.zenite.telas.fiscal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.orion.zenite.R
import kotlinx.android.synthetic.main.activity_main_fiscal.*

class QtdPassageiros : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qtd_passageiros)

        //        https://material.io/develop/android/components/app-bars-top
        topAppBar.setNavigationOnClickListener {
            this.finish()
        }
    }

    fun voltar(view: View){
        val intent = Intent(this, MainFiscal::class.java)
        startActivity(intent)
    }
}