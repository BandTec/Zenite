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
import kotlinx.android.synthetic.main.activity_cronograma_linha.*
import kotlinx.android.synthetic.main.activity_cronograma_linha.listViagens
import kotlinx.android.synthetic.main.activity_cronograma_linha.topAppBar
import kotlinx.android.synthetic.main.activity_linha_motorista.*
import kotlinx.android.synthetic.main.fragment_viagens_diarias.*
import kotlinx.android.synthetic.main.fragment_viagens_semanais.*

class LinhaCronograma : AppCompatActivity() {

    private val horarios = arrayListOf<Cronograma>(
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "Nicole Brito", "22:50 - 23:48", true),
        Cronograma("22:10 - 22:40", "22:50 - 23:50", "Marias Callas", "22:50 - 23:48", false),
        Cronograma("", "22:50 - 23:50", "Luciano Pavarotti", "", false)
    )


    private var lista: RecyclerView? = null
    private var nomeLinha: String? = "";
    val listaCronograma = MutableLiveData<List<Cronograma>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var swipe: SwipeRefreshLayout? = null
    val empty = MutableLiveData<Boolean>()

    // adapter do recycleview
    private val listaAdapter = CronogramaAdapter(arrayListOf())

    var id: Int? = null
    var token: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cronograma_linha)

        nomeLinha = intent.extras?.getString("nomeLinha")
        topAppBar.title = nomeLinha
        // titulo_tela.text = nomeLinha

        topAppBar.setNavigationOnClickListener {
            this.finish()
        }

        id = intent.extras?.getInt("id")
        token = intent.extras?.getString("token").toString()
        Toast.makeText(this, "olha esse $token e esse $id", Toast.LENGTH_SHORT).show()


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

        // TODO: REMOVER DADOS ESTATICOS => IDFISCAL E JWT TOKEN
        val idUser = 4
        val token =
            "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"


        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
        // TODO ROTA E DESCOMENTAR ABAIXO
        if (id != null) {
//        val listaRemoto: Call<List<Viagens>> = service.getViagensDiarias(id!!, token)
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
            listaCronograma.value = horarios;
//                loadError.value = false;
//                loading.value = false;
//
//            if(response.body()?.toList() === null) {
//                empty.value = true
//            }
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