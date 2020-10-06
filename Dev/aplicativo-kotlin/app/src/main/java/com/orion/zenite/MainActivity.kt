package com.orion.zenite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.orion.zenite.fiscal.FiscalLinhas
import com.orion.zenite.fiscal.MainFiscal
import com.orion.zenite.autenticacao.Login
import com.orion.zenite.motorista.MainMotorista
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goDash(view: View){
        val campoTexto = entrar.text;
        if(campoTexto.isNotEmpty()){
            if(campoTexto.toString().toLowerCase().equals("motorista")){
                val intent = Intent(this@MainActivity, MainMotorista::class.java)
                startActivity(intent)
            }else {
                val intent = Intent(this@MainActivity, MainFiscal::class.java)
                startActivity(intent)
            }
        }
    }

    fun testeOutraTela(view: View) {
        val intent = Intent(this@MainActivity, FiscalLinhas::class.java)
        startActivity(intent)
    }

    fun irTelaLogin(view: View){
        val LoginActivity = Intent(this@MainActivity, Login::class.java)

        startActivity(LoginActivity)
    }
}