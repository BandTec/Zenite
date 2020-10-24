package com.orion.zenite.telas.fiscal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.listAdapters.CronogramaAdapter
import com.orion.zenite.model.Cronograma
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar

class LinhaCronograma : AppCompatActivity() {

    private var lista: RecyclerView? = null
    private var nomeLinha: String? = "";

    private val horarios = arrayListOf<Cronograma>(
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "Nicole Brito", "22:50 - 23:48", true),
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "Marias Callas", "22:50 - 23:48", false),
        Cronograma("", "22:50 - 23:50", "Luciano Pavarotti", "", false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronograma_linha)

        nomeLinha = intent.extras?.getString("nomeLinha")
        topAppBar.title = nomeLinha
        // titulo_tela.text = nomeLinha

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }

        lista = listViagens as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(this@LinhaCronograma)
            adapter = CronogramaAdapter(horarios)
        }

        lista!!.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }

    fun alterarIntervalo(view: View){
        val intent = Intent(this, AlterarIntervalo::class.java)
        intent.putExtra("nomeLinha", nomeLinha)
        startActivity(intent)
    }
}