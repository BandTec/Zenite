package com.orion.zenite.telas.motorista.fragments.viagens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.listAdapters.HistoricoAdapter
import com.orion.zenite.listAdapters.ViagensAdapter
import com.orion.zenite.model.HistoricoViagens
import com.orion.zenite.model.Viagens
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.fragment_viagens_diarias.*
import kotlinx.android.synthetic.main.fragment_viagens_semanais.*

class ViagensSemanais : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    // nested reclycler view
    // https://android.jlelse.eu/easily-adding-nested-recycler-view-in-android-a7e9f7f04047



    private val viagens = arrayListOf<Viagens>(
        Viagens("14:00 - 14:30", "30 MIN"),
        Viagens("14:40 - 15:30", "40 MIN"),
        Viagens("15:40 - 16:40", "1 H"),
        Viagens("16:50 - 17:20", "30 MIN"),
        Viagens("17:30 - 18:10", "30 MIN"),
        Viagens("18:20 - 18:50", "30 MIN")
    )

    private val dadosTemporarios = arrayListOf<HistoricoViagens>(
        HistoricoViagens("23/08/20", viagens),
        HistoricoViagens("22/08/20", viagens),
        HistoricoViagens("21/08/20", viagens)
    )

    private var lista: RecyclerView? = null
    val listaViagens = MutableLiveData<List<HistoricoViagens>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var swipe: SwipeRefreshLayout? = null
    val empty = MutableLiveData<Boolean>()
    // adapter do recycleview
    private val listaAdapter = HistoricoAdapter(arrayListOf())
    var id :Int? = null
    var token : String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_viagens_semanais, container, false)

        lista = view.findViewById(R.id.listViagens) as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listaAdapter
        }

         id = getActivity()?.getIntent()?.extras?.getInt("id")
         token = getActivity()?.getIntent()?.extras?.getString("token").toString()

        // chama api
        refresh()

        // aplica função de refresh ao componente swipe refresh
        swipe = view.findViewById(R.id.swipeViagensSemanais) as SwipeRefreshLayout
        swipe!!.setOnRefreshListener {
            swipeViagensSemanais.isRefreshing = false
            refresh()
        }

        return view
    }

    private fun consumirApi() {
        loading.value = true;

        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
        // TODO ROTA E DESCOMENTAR ABAIXO

        if(id != null) {
//        val listaRemoto: Call<List<Viagens>> = service.getViagensSemanais(id!!, token)
//
//        listaRemoto.enqueue(object : Callback<List<Viagens>> {
//            override fun onFailure(call: Call<List<Viagens>>, t: Throwable) {
//                loadError.value = true;
//                loading.value = false;
//
//                println("deu ruim = ${t.message}")
//            }
//
//            override fun onResponse(call: Call<List<Viagens>>, response: Response<List<Viagens>>) {
//                listaViagens.value = response.body()?.toList()
            listaViagens.value = dadosTemporarios;
//                loadError.value = false;
//                loading.value = false;
//              if(response.body()?.toList() === null) {
//                        empty.value = true
//                    }
//                println("status code = ${response.code()}")
//            }
//        })
        } else {
            loadError.value = true;
            loading.value = false;
        }
        loading.value = false;
    }


    private fun refresh() {
        consumirApi()

        empty.observe(this, Observer { isEmpty ->
            isEmpty?.let { viagemVazia.visibility = if (it) View.VISIBLE else View.GONE }

        })

        listaViagens.observe(this, Observer { linhas ->
            linhas?.let {
                layout_viagemsSemanais?.visibility = View.VISIBLE

                listaAdapter.update(it)
            }
        })

        loadError.observe(this, Observer { isError ->
            isError?.let { erroSemana.visibility = if (it) View.VISIBLE else View.GONE }

        })

        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loaderSemanal.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    erroSemana.visibility = View.GONE
                    viagemVazia.visibility = View.GONE
                    layout_viagemsSemanais?.visibility = View.GONE
                }
            }
        })
    }

}
