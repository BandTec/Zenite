package com.orion.zenite.telas.motorista.fragments.viagens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.listAdapters.HistoricoAdapter
import com.orion.zenite.model.HistoricoViagens
import com.orion.zenite.model.Viagens

class ViagensSemanais : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    // nested reclycler view
    // https://android.jlelse.eu/easily-adding-nested-recycler-view-in-android-a7e9f7f04047

    private var lista: RecyclerView? = null

    private val viagens = listOf(
        Viagens("14:00 - 14:30", "30 MIN"),
        Viagens("14:40 - 15:30", "40 MIN"),
        Viagens("15:40 - 16:40", "1 H"),
        Viagens("16:50 - 17:20", "30 MIN"),
        Viagens("17:30 - 18:10", "30 MIN"),
        Viagens("18:20 - 18:50", "30 MIN")
    )

    private val dadosTemporarios = listOf(
        HistoricoViagens("23/08/20", viagens),
        HistoricoViagens("22/08/20", viagens),
        HistoricoViagens("21/08/20", viagens)
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_viagens_semanais, container, false)

        lista = view.findViewById(R.id.listViagens) as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = HistoricoAdapter(dadosTemporarios)
        }

        return view
    }

}
