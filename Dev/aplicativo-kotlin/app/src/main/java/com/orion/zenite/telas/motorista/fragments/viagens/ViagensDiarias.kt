package com.orion.zenite.telas.motorista.fragments.viagens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.http.motorista.MotoristaApi
import com.orion.zenite.listAdapters.LinhasAdapter
import com.orion.zenite.model.Viagens
import com.orion.zenite.listAdapters.ViagensAdapter
import com.orion.zenite.model.Linha
import com.orion.zenite.model.ViagemDiaria
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.fragment_linhas.*
import kotlinx.android.synthetic.main.fragment_viagens_diarias.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViagensDiarias : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    private var lista: RecyclerView? = null
    val listaViagens = MutableLiveData<List<Viagens>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var swipe: SwipeRefreshLayout? = null
    val empty = MutableLiveData<Boolean>()

    // adapter do recycleview
    private val listaAdapter = ViagensAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_viagens_diarias, container, false)

        lista = view.findViewById(R.id.listViagens) as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listaAdapter
        }

        // chama api
        refresh()

        // aplica função de refresh ao componente swipe refresh
        swipe = view.findViewById(R.id.swipeViagensDiarias) as SwipeRefreshLayout
        swipe!!.setOnRefreshListener {
            swipeViagensDiarias.isRefreshing = false
            refresh()
        }
        return view
    }


    private fun consumirApi() {
        loading.value = true;

        val service: MotoristaApi = HttpHelper().getApiClient()!!.create(MotoristaApi::class.java)
        // TODO ROTA E DESCOMENTAR ABAIXO

        val id = AppPreferencias.id
        val token = AppPreferencias.token

        val listaRemoto: Call<ViagemDiaria> = service.consultarViagensDia(id, token)

        listaRemoto.enqueue(object : Callback<ViagemDiaria> {
            override fun onFailure(call: Call<ViagemDiaria>, t: Throwable) {
                loadError.value = true;
                loading.value = false;

                println("deu ruim = ${t.message}")
            }

            override fun onResponse(call: Call<ViagemDiaria>, response: Response<ViagemDiaria>) {
                val resposta = response.body()
                println("status code = ${response.code()}")
                println("resposta = ${response}")
                if(resposta?.listaViagens === null) {
                    empty.value = true
                } else {
                    listaViagens.value = resposta.listaViagens.toList()
                    loadError.value = false;
                    loading.value = false;
                    realizada_valor.text = resposta.viagensRealizadas.toString()
                    restante_valor.text = resposta.viagensRestantes.toString()
                }
            }
        })

        loading.value = false;
    }


    private fun refresh() {
        consumirApi()

        empty.observe(this, Observer { isEmpty ->
            isEmpty?.let { viagemVaziaDiaria.visibility = if (it) View.VISIBLE else View.GONE }

        })

        listaViagens.observe(this, Observer { linhas ->
            linhas?.let {
                layout_viagemsDiarias?.visibility = View.VISIBLE

                listaAdapter.update(it)
            }
        })

        loadError.observe(this, Observer { isError ->
            isError?.let { erro.visibility = if (it) View.VISIBLE else View.GONE }

        })

        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loaderDiario.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    erro.visibility = View.GONE
                    viagemVaziaDiaria.visibility = View.GONE
                    layout_viagemsDiarias?.visibility = View.GONE
                }
            }
        })
    }
}