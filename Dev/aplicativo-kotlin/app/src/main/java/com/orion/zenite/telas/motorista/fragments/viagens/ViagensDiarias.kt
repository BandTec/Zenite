package com.orion.zenite.telas.motorista.fragments.viagens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.Viagens
import com.orion.zenite.listAdapters.ViagensAdapter

class ViagensDiarias : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    private val dadosTemporarios = listOf(
        Viagens("14:00 - 14:30", "30 MIN"),
        Viagens("14:40 - 15:30", "40 MIN"),
        Viagens("15:40 - 16:40", "1 H"),
        Viagens("16:50 - 17:20", "30 MIN"),
        Viagens("17:30 - 18:10", "30 MIN"),
        Viagens("18:20 - 18:50", "30 MIN")
        )

    private var lista: RecyclerView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_viagens_diarias, container, false)

        lista = view.findViewById(R.id.listViagens) as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = ViagensAdapter(dadosTemporarios)
        }

        return view
    }

}