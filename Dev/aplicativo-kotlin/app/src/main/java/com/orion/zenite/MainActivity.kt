package com.orion.zenite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.orion.zenite.fiscal.FiscalLinhas
import com.orion.zenite.fiscal.MainFiscal
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.motorista.MainMotorista
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goDash(view: View){
        val campoTexto = entrar.text;
        if(campoTexto.isNotEmpty()){
            if(campoTexto.toString().toLowerCase().equals("m")){
                val intent = Intent(this, MainMotorista::class.java)
                startActivity(intent)
            }else {
                val intent = Intent(this, MainFiscal::class.java)
                startActivity(intent)
            }
        }
    }

    fun testeOutraTela(view: View) {
        val intent = Intent(this, FiscalLinhas::class.java)
        startActivity(intent)
    }
}