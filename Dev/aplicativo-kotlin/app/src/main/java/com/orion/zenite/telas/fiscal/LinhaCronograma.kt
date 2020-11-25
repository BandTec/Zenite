package com.orion.zenite.telas.fiscal

import android.content.Intent
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
import com.orion.zenite.listAdapters.CronogramaAdapter
import com.orion.zenite.listAdapters.ViagensAdapter
import com.orion.zenite.model.Cronograma
import com.orion.zenite.model.Viagens
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.listViagens
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.fragment_viagens_diarias.*
import kotlinx.android.synthetic.main.fragment_viagens_semanais.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LinhaCronograma : AppCompatActivity() {

    private var lista: RecyclerView? = null
    private var swipe: SwipeRefreshLayout? = null
    private var nomeLinha: String? = "";

    val listaCronograma = MutableLiveData<List<Cronograma>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    val empty = MutableLiveData<Boolean>()

    // adapter do recycleview
    private val listaAdapter = CronogramaAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronograma_linha)

        nomeLinha = intent.extras?.getString("nomeLinha")
        topAppBar.title = nomeLinha
        // titulo_tela.text = nomeLinha

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }

        lista = listViagens as RecyclerView
        lista!!.apply {
            layoutManager = LinearLayoutManager(this@LinhaCronograma)
            adapter = listaAdapter
        }

        lista!!.addItemDecoration(
            DividerItemDecoration(
                this,
                LinearLayoutManager.VERTICAL
            )
        )

        // chama api
        refresh()

        // aplica função de refresh ao componente swipe refresh
        swipe = this.findViewById(R.id.swipeCronograma) as SwipeRefreshLayout
        swipe!!.setOnRefreshListener {
            swipeCronograma.isRefreshing = false
            refresh()
        }
    }

    fun alterarIntervalo(view: View) {
        val intent = Intent(this, AlterarIntervalo::class.java)
        intent.putExtra("nomeLinha", nomeLinha)
        startActivity(intent)
    }


    private fun consumirApi() {
        loading.value = true;
        empty.value = false

        val idLinha = intent.extras?.getInt("idLinha")
        val token = AppPreferencias.token

        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)

        if (idLinha != null) {
            val listaRemoto: Call<List<Cronograma>> = service.getLinhaCronograma(idLinha, token)

            listaRemoto.enqueue(object : Callback<List<Cronograma>> {
                override fun onFailure(call: Call<List<Cronograma>>, t: Throwable) {
                    loadError.value = true;
                    loading.value = false;

                    println("deu ruim = ${t.message}")
                }

                override fun onResponse(
                    call: Call<List<Cronograma>>,
                    response: Response<List<Cronograma>>
                ) {
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
                listViagens?.visibility = View.VISIBLE
                listaAdapter.update(it)
            }
        })

        loadError.observe(this, Observer { isError ->
            isError?.let { erroCronograma.visibility = if (it) View.VISIBLE else View.GONE }

        })

        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                loaderCro.visibility = if (it) View.VISIBLE else View.GONE
                if (it) {
                    cronogramaVazio.visibility = View.GONE
                    erroCronograma.visibility = View.GONE
                    listViagens?.visibility = View.GONE
                }
            }
        })
    }
}