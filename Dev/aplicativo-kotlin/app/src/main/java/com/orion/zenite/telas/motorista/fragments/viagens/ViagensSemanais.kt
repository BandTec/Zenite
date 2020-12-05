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
import com.orion.zenite.http.motorista.MotoristaApi
import com.orion.zenite.listAdapters.HistoricoAdapter
import com.orion.zenite.listAdapters.ViagensAdapter
import com.orion.zenite.model.HistoricoViagens
import com.orion.zenite.model.MotoristaViagens
import com.orion.zenite.model.Viagens
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.fragment_viagens_diarias.*
import kotlinx.android.synthetic.main.fragment_viagens_semanais.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViagensSemanais : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    // nested reclycler view
    // https://android.jlelse.eu/easily-adding-nested-recycler-view-in-android-a7e9f7f04047


    private var lista: RecyclerView? = null
    val listaViagens = MutableLiveData<List<MotoristaViagens>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    val empty = MutableLiveData<Boolean>()

    // adapter do recycleview
    private val listaAdapter = HistoricoAdapter(arrayListOf())

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

        // chama api
        refresh()

        return view
    }

    private fun consumirApi() {
        loading.value = true;
        empty.value = false
        loadError.value = false;
        val id = AppPreferencias.id
        val token = AppPreferencias.token
        val service: MotoristaApi = HttpHelper().getApiClient()!!.create(MotoristaApi::class.java)

        val listaRemoto: Call<HistoricoViagens> = service.consultarTodasViagens(id, token)

        listaRemoto.enqueue(object : Callback<HistoricoViagens> {
            override fun onFailure(call: Call<HistoricoViagens>, t: Throwable) {
                loadError.value = true;
                loading.value = false;
                println("deu ruim = ${t.message}")
            }

            override fun onResponse(
                call: Call<HistoricoViagens>,
                response: Response<HistoricoViagens>
            ) {
                println("resposta = ${response}")
                println("status code = ${response.code()}")
                val resposta = response.body()
                if (resposta === null) {
                    empty.value = true
                } else {
                    listaViagens.value = resposta.viagens
                    total_viagens.text = resposta.qtd.toString()
                }
                loading.value = false;
            }
        })


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
