package com.orion.zenite.telas.fiscal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.listAdapters.MotoristaOnibusAdapter
import com.orion.zenite.model.MotoristaOnibus
import kotlinx.android.synthetic.main.activity_linha_motorista.*

class LinhaMotorista : AppCompatActivity() {
    private var lista: RecyclerView? = null

    private val dadosTemp = listOf(
        MotoristaOnibus("Maria Carla", "9 9856-6544", "BMJ-1123"),
        MotoristaOnibus("Nicole Fernanda Brito", "5 9856-6544", "1MJ-1123"),
        MotoristaOnibus("Isaac Enrico Ferreira", "4 9856-6544", "2MJ-1123"),
        MotoristaOnibus("Martin Bryan Costa", "3 9856-6544", "3MJ-1123"),
        MotoristaOnibus("Joyce Didonato", "1 9856-6544", "4MJ-1123"),
        MotoristaOnibus("Maria Damrau", "2 9856-6544", "5MJ-1123")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linha_motorista)

        val nomeLinha = intent.extras?.getString("nomeLinha")
        top_bar_motorista.title = nomeLinha
        // titulo_tela.text = nomeLinha

        top_bar_motorista.setNavigationOnClickListener {
            this.finish()
        }

        lista = listaMotorista as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(this@LinhaMotorista)
            adapter = MotoristaOnibusAdapter(dadosTemp)
        }
        lista!!.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )
    }


}