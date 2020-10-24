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
import com.orion.zenite.listAdapters.LinhasAdapter
import com.orion.zenite.model.Viagens
import com.orion.zenite.listAdapters.ViagensAdapter
import com.orion.zenite.model.Linha
import kotlinx.android.synthetic.main.fragment_linhas.*
import kotlinx.android.synthetic.main.fragment_viagens_diarias.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViagensDiarias : Fragment() {

    // https://www.alura.com.br/artigos/criando-listas-com-recyclerview
    // https://androidwave.com/recyclerview-kotlin-tutorial/
    // https://medium.com/@hinchman_amanda/working-with-recyclerview-in-android-kotlin-84a62aef94ec



    private val dadosTemporarios = arrayListOf<Viagens>(
        Viagens("14:00 - 14:30", "30 MIN"),
        Viagens("14:40 - 15:30", "40 MIN"),
        Viagens("15:40 - 16:40", "1 H"),
        Viagens("16:50 - 17:20", "30 MIN"),
        Viagens("17:30 - 18:10", "30 MIN"),
        Viagens("18:20 - 18:50", "30 MIN")
        )

    private var lista: RecyclerView? = null
    val listaViagens = MutableLiveData<List<Viagens>>()
    val loadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()
    private var swipe: SwipeRefreshLayout? = null

    // adapter do recycleview
    private val listaAdapter = ViagensAdapter(arrayListOf())


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_viagens_diarias, container, false)


        val id = getActivity()?.getIntent()?.extras?.getInt("id")
        val token = getActivity()?.getIntent()?.extras?.getString("token").toString()
        // Toast.makeText(activity, "olha esse $token e esse $id", Toast.LENGTH_SHORT).show()

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

        // TODO: REMOVER DADOS ESTATICOS => IDFISCAL E JWT TOKEN
        val idUser = 4
        val token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1AYWRtLmNvbS5iciIsImV4cCI6Mzc4ODAyNTM3MzV9.Tpcmo2fxO4DPaekU-CbXYiH9O95f2RqWHUMd1dcNO6s"


        val service: FiscalApi = HttpHelper().getApiClient()!!.create(FiscalApi::class.java)
        // TODO ROTA E DESCOMENTAR ABAIXO

//        val listaRemoto: Call<List<Viagens>> = service.getViagensDiarias(idUser, token)
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
//
//                println("status code = ${response.code()}")
//            }
//        })
        loading.value = false;
    }


    private fun refresh() {
        consumirApi()

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
                    layout_viagemsDiarias?.visibility = View.GONE
                }
            }
        })
    }
}