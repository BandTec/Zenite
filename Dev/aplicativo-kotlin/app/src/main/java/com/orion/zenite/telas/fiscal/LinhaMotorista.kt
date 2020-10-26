package com.orion.zenite.telas.fiscal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.fiscal.FiscalApi
import com.orion.zenite.listAdapters.MotoristaOnibusAdapter
import com.orion.zenite.model.Linha
import com.orion.zenite.model.Onibus
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.activity_linha_motorista.list_error
import kotlinx.android.synthetic.main.activity_linha_motorista.loading_view
import kotlinx.android.synthetic.main.fragment_linhas.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LinhaMotorista : AppCompatActivity() {
    private var lista: RecyclerView? = null
    private var swipe: SwipeRefreshLayout? = null

    // adapter do recycleview
    private val listaAdapter = MotoristaOnibusAdapter(arrayListOf())

    // variaveis para armazenar conteudo da api, se esta carregando e se houve erro
    val linhas = MutableLiveData<List<Onibus>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()
    var id :Int? = null
    var token : String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linha_motorista)

        // ao clicar na seta de voltar "remove" a tela atual da pilha de telas
        top_bar_motorista.setNavigationOnClickListener {
            this.finish()
        }

        id = intent.extras?.getInt("id")
        token = intent.extras?.getString("token").toString()


        // pegando nome da linha e colocando na barra de navegação superior
        val nomeLinha = intent.extras?.getString("nomeLinha")
        top_bar_motorista.title = nomeLinha

        // adiciona o adapter de conteudo ao recycler view
        lista = listaMotorista as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(this@LinhaMotorista)
            adapter = listaAdapter
        }

        // adicionar linhas entre os itens da listas
        lista!!.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        // chama api
        refresh()

        // aplica função de refresh ao componente swipe refresh
        swipe = this.findViewById(R.id.swipeRefreshLayout) as SwipeRefreshLayout
        swipe!!.setOnRefreshListener {
            swipe!!.isRefreshing = false
            refresh()
        }
    }


    // consome a api
    // chamando retrofit da classe httphelper
    // e interface FiscalApi


    private fun consumirApi() {
        loading.value = true;

        // : REMOVER DADOS ESTATICOS => JWT TOKEN
      //  val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"

        // armazenando id da linha vindo da tela anterior
        val idLinha = intent.extras?.getInt("idLinha")

        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
        val listaRemoto: Call<List<Onibus>>? =
            idLinha?.let { service.getLinhaMotoristaOnibus(it, token) }

        if (listaRemoto != null) {
            listaRemoto.enqueue(object : Callback<List<Onibus>> {
                override fun onFailure(call: Call<List<Onibus>>, t: Throwable) {
                    loadError.value = true
                    loading.value = false

                    println("deu ruim = ${t.message}")
                }

                override fun onResponse(call: Call<List<Onibus>>, response: Response<List<Onibus>>) {
                    linhas.value = response.body()?.toList()
                    loadError.value = false
                    loading.value = false

                    if(response.body()?.toList() === null) {
                        empty.value = true
                    }

                    println("status code = ${response.code()}")
                }
            })
        }

    }

    private fun refresh() {
        consumirApi()

        // atualiza conteudo do recycler view e mostra a lista
        linhas.observe(this, Observer { linhas ->
            linhas?.let {
                lista?.visibility = View.VISIBLE
                listaAdapter.update(it)
            }
        })

        // se houve erro mostra menssagem de erro
        loadError.observe(this, Observer { isError ->
            isError?.let { list_error.visibility = if (it) View.VISIBLE else View.GONE }

        })

        // se for vazio
        empty.observe(this, Observer { isEmpty ->
            isEmpty?.let { list_empty.visibility = if (it) View.VISIBLE else View.GONE }

        })

        // se está carregando esconde list ou mensagem de erro e mostras loader
        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    list_empty.visibility = View.GONE
                    list_error.visibility = View.GONE
                    lista?.visibility = View.GONE
                }
            }
        })
    }

}