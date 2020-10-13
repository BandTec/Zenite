package com.orion.zenite.telas.fiscal.fragments.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.telas.fiscal.LinhaCronograma
import com.orion.zenite.R
import com.orion.zenite.listAdapters.CronogramaGeralAdapter
import com.orion.zenite.model.Cronograma
import com.orion.zenite.model.CronogramaGeral

class CronogramaGeral : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    // nested reclycler view
    // https://android.jlelse.eu/easily-adding-nested-recycler-view-in-android-a7e9f7f04047

    private var lista: RecyclerView? = null

    private val horarios = listOf(
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "NicoleBrito", "", false),
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "NicoleBrito", "", false),
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "NicoleBrito", "", false)
    )

    private val dadosTemporarios = listOf(
        CronogramaGeral("8001-10 Term. Piaui", horarios),
        CronogramaGeral("917H-10 Vila Mariana", horarios),
        CronogramaGeral("8004-10 Term. Lapa", horarios)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cronograma_geral, container, false)

        // o componente recycler view é utilizado para gerar a lista
        // ele precisa de uma classe customizada para adaptar os dados em sua lista para o layout criado,
        // também pode-se passar uma função para ser utilizada no clique dos items da lista

        lista = view.findViewById(R.id.listCronograma) as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = CronogramaGeralAdapter(dadosTemporarios) { cronograma: CronogramaGeral -> onItemClick(cronograma)}
        }

        return view
    }

    private fun onItemClick(cronograma: CronogramaGeral) {
        // Toast.makeText(activity, "Você clicou em: ${cronograma.nomeLinha}", Toast.LENGTH_SHORT).show()

        val intent = Intent(activity, LinhaCronograma::class.java)
        intent.putExtra("nomeLinha", cronograma.nomeLinha)
        startActivity(intent)
    }

}