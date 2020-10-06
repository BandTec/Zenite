package com.orion.zenite.fiscal.dashboard

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.listAdapters.HistoricoAdapter
import com.orion.zenite.listAdapters.LinhasAdapter
import com.orion.zenite.listAdapters.ViagensAdapter
import com.orion.zenite.model.Cronograma
import com.orion.zenite.model.Viagens

class Linhas : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    private val dadosTemporarios = listOf(
        "8004-10 Term. Lapa",
        "917H-10 VILA MARIANA",
        "8001-10 TERM. PIAUI",
        "8002-10 TERM. PIRITUBA"
    )

    private var lista: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_linhas, container, false)

        lista = view.findViewById(R.id.listaLinhas) as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = LinhasAdapter(dadosTemporarios)
        }


        return view
    }

}