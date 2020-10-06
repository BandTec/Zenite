package com.orion.zenite.fiscal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.listAdapters.CronogramaAdapter
import com.orion.zenite.listAdapters.CronogramaGeralAdapter
import com.orion.zenite.model.Cronograma
import com.orion.zenite.model.CronogramaGeral
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar
import kotlinx.android.synthetic.main.activity_main_fiscal.*
import kotlinx.android.synthetic.main.fragment_linhas.*

class CronogramaLinha : AppCompatActivity() {

    private var lista: RecyclerView? = null

    private val horarios = listOf(
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "NicoleBrito", "em viagem"),
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "NicoleBrito", "em viagem"),
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "NicoleBrito", "em viagem")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronograma_linha)

        val nomeLinha = intent.extras?.getString("nomeLinha")
        topAppBar.title = nomeLinha

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }

        lista = listViagens as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(this@CronogramaLinha)
            adapter = CronogramaAdapter(horarios)
        }
    }

    fun alterarIntervalo(view: View){
        Toast.makeText(this, "Você clicou em OLá", Toast.LENGTH_SHORT).show()
//        val intent = Intent(this, CronogramaLinha::class.java)
//        intent.putExtra("nomeLinha", nomeLinha)
//        startActivity(intent)
    }
}