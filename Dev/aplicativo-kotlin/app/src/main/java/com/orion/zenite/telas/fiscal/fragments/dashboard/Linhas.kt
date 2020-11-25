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
import com.orion.zenite.R
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.telas.fiscal.LinhaDetalhes
import com.orion.zenite.listAdapters.LinhasAdapter
import com.orion.zenite.model.Linha
import com.orion.zenite.telas.fiscal.LinhaCronograma
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.fragment_linhas.*
import kotlinx.android.synthetic.main.fragment_linhas.list_error
import kotlinx.android.synthetic.main.fragment_linhas.loading_view
import kotlinx.android.synthetic.main.fragment_linhas.swipeRefreshLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Linhas : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec

    // componentes do layout
    private var lista: RecyclerView? = null
    private var swipe: SwipeRefreshLayout? = null

    // adapter do recycleview
    private val linhasAdapter = LinhasAdapter(arrayListOf()) { linha: Linha -> onItemClick(linha) }

    // variaveis para armazenar conteudo da api, se esta carregando e se houve erro
    val linhas = MutableLiveData<List<Linha>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_linhas, container, false)

        // aplica adaptador da lista no componente recycler view
        lista = view.findViewById(R.id.listaLinhas) as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(activity)
            adapter = linhasAdapter
        }

        // chama api
        refresh()

        // aplica função de refresh ao componente swipe refresh
        swipe = view.findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipe!!.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            refresh()
        }

        return view
    }

    // consome a api
    // chamando retrofit da classe httphelper
    // e interface FiscalApi

    private fun consumirApi() {
        loading.value = true;
        val id = AppPreferencias.id
        val token = AppPreferencias.token

        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)

            val listaRemoto: Call<List<Linha>> = service.getFiscalLinhas(id, token)
            //Toast.makeText(activity, "olha esse $token e esse $id", Toast.LENGTH_SHORT).show()
            listaRemoto.enqueue(object : Callback<List<Linha>> {
                override fun onFailure(call: Call<List<Linha>>, t: Throwable) {
                    loadError.value = true;
                    loading.value = false;

                    println("deu ruim = ${t.message}")
                }

                override fun onResponse(call: Call<List<Linha>>, response: Response<List<Linha>>) {
                    linhas.value = response.body()?.toList()
                    loadError.value = false;
                    loading.value = false;

                    if(response.body()?.toList() === null) {
                        empty.value = true
                    }

                    println("status code = ${response.code()}")
                }
            })
    }


    private fun refresh() {
        consumirApi()

        empty.observe(this, Observer { isEmpty ->
            isEmpty?.let { linhasVazias.visibility = if (it) View.VISIBLE else View.GONE }

        })

        linhas.observe(this, Observer { linhas ->
            linhas?.let {
                lista?.visibility = View.VISIBLE
                linhasAdapter.update(it)
            }
        })

        loadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }

        })

        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_error.visibility = View.GONE
                    linhasVazias.visibility = View.GONE
                    lista?.visibility = View.GONE
                }
            }
        })
    }

    private fun onItemClick(linha: Linha) {
        val intent = Intent(activity, LinhaDetalhes::class.java)
        intent.putExtra("linha", linha.numero)
        intent.putExtra("idLinha", linha.id)
        startActivity(intent)
    }

}
