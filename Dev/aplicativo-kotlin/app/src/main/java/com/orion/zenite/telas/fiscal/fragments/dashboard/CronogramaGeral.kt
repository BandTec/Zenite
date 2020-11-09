package com.orion.zenite.telas.fiscal.fragments.dashboard

import android.content.Intent
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
import com.orion.zenite.telas.fiscal.LinhaCronograma
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.listAdapters.CronogramaGeralAdapter
import com.orion.zenite.listAdapters.ViagensAdapter
import com.orion.zenite.model.Cronograma
import com.orion.zenite.model.CronogramaGeral
import com.orion.zenite.model.Viagens
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.fragment_cronograma_geral.*
import kotlinx.android.synthetic.main.fragment_viagens_diarias.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CronogramaGeral : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    // nested reclycler view
    // https://android.jlelse.eu/easily-adding-nested-recycler-view-in-android-a7e9f7f04047

    private var lista: RecyclerView? = null

    val listaCronograma = MutableLiveData<List<CronogramaGeral>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()
    private var swipe: SwipeRefreshLayout? = null

    // adapter do recycleview
    private val listaAdapter = CronogramaGeralAdapter(arrayListOf()){ cronograma: CronogramaGeral -> onItemClick(cronograma)}
    var id :Int? = null
    var token : String = ""


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cronograma_geral, container, false)


        id = activity?.intent?.extras?.getInt("id")
        token = activity?.intent?.extras?.getString("token").toString()

        // o componente recycler view é utilizado para gerar a lista
        // ele precisa de uma classe customizada para adaptar os dados em sua lista para o layout criado,
        // também pode-se passar uma função para ser utilizada no clique dos items da lista

        lista = view.findViewById(R.id.listCronograma) as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = listaAdapter
        }

        // chama api
        refresh()

        // aplica função de refresh ao componente swipe refresh
        swipe = view.findViewById(R.id.swipeCronogramaGeral) as SwipeRefreshLayout
        swipe!!.setOnRefreshListener {
            swipeCronogramaGeral.isRefreshing = false
            refresh()
        }

        return view
    }

    private fun onItemClick(cronograma: CronogramaGeral) {
        // Toast.makeText(activity, "Você clicou em: ${cronograma.nomeLinha}", Toast.LENGTH_SHORT).show()

        val intent = Intent(activity, LinhaCronograma::class.java)
        intent.putExtra("nomeLinha", cronograma.nomeLinha)
        intent.putExtra("idLinha", cronograma.idLinha)
        intent.putExtra("token", token)
        intent.putExtra("id", id)
        startActivity(intent)
    }


    private fun consumirApi() {
        loading.value = true
        empty.value = false
        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
        // TODO ROTA E DESCOMENTAR ABAIXO

        if(id != null) {
        val listaRemoto: Call<List<CronogramaGeral>> = service.getCronogramaGeral(id!!, token)

        listaRemoto.enqueue(object : Callback<List<CronogramaGeral>> {
            override fun onFailure(call: Call<List<CronogramaGeral>>, t: Throwable) {
                loadError.value = true;
                loading.value = false;

                println("deu ruim = ${t.message}")
            }

            override fun onResponse(call: Call<List<CronogramaGeral>>, response: Response<List<CronogramaGeral>>) {
                listaCronograma.value = response.body()?.toList()
                loadError.value = false;
                loading.value = false;

                if (response.body()?.toList() === null) {
                    empty.value = true
                }
                println("status code = ${response.code()}")
            }
        })
        } else {
            loadError.value = true;
            loading.value = false;
        }
        loading.value = false;
    }


    private fun refresh() {
        consumirApi()

        empty.observe(this, Observer { isEmpty ->
            isEmpty?.let { cronogramaVazio.visibility = if (it) View.VISIBLE else View.GONE }

        })

        listaCronograma.observe(this, Observer { linhas ->
            linhas?.let {
                listCronograma?.visibility = View.VISIBLE
                listaAdapter.update(it)
            }
        })

        loadError.observe(this, Observer { isError ->
            isError?.let { erroCronogramaGeral.visibility = if (it) View.VISIBLE else View.GONE }

        })

        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loaderCroGeral.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    erroCronogramaGeral.visibility = View.GONE
                    listCronograma?.visibility = View.GONE
                    cronogramaVazio.visibility = View.GONE
                }
            }
        })
    }
}