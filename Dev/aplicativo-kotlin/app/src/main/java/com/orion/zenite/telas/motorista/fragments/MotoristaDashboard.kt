package com.orion.zenite.telas.motorista.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orion.zenite.R
import com.orion.zenite.http.HttpHelper
import com.orion.zenite.http.autenticacao.LoginApi
import com.orion.zenite.http.motorista.MotoristaApi
import com.orion.zenite.model.CronogramaHorarioSimples
import com.orion.zenite.model.UserZenite
import com.orion.zenite.model.Viagens
import com.orion.zenite.utils.AppPreferencias
import kotlinx.android.synthetic.main.fragment_linhas.*
import kotlinx.android.synthetic.main.fragment_motorista_dashboard.*
import kotlinx.android.synthetic.main.fragment_motorista_qrcode.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MotoristaDashboard : Fragment() {

    val loading = MutableLiveData<Boolean>()
    private var swipe: SwipeRefreshLayout? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_motorista_dashboard, container, false)

        val id = AppPreferencias.id
        val token = AppPreferencias.token

        refresh(token, id)

        swipe = view.findViewById(R.id.swipeRefresh) as SwipeRefreshLayout
        swipe!!.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            refresh(token, id)
        }

        return view;
    }

    private fun refresh(token: String, id: Int) {
        buscarDadosConta(token)
        viagemAtual(id, token)

        loading.value = true
        loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                if (it) {
                    loader.visibility = View.VISIBLE
                    layoutMotorista.visibility = View.GONE
                } else {
                    loader.visibility = View.GONE
                    layoutMotorista.visibility = View.VISIBLE
                }
            }
        })
    }


    fun buscarDadosConta(token: String) {
        val requests: LoginApi = HttpHelper().getApiClient()!!.create(LoginApi::class.java)

        val resultado = requests.getDadosConta(token)

        resultado.enqueue(object : Callback<UserZenite> {
            override fun onFailure(call: Call<UserZenite>, t: Throwable) {
                Toast.makeText(activity, getString(R.string.erro_ao_carregar), Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onResponse(call: Call<UserZenite>, response: Response<UserZenite>) {

                try {
                    val motorista = response.body()

                    boasVindas.text =
                        getString(R.string.motorista_welcome).format(motorista!!.nome)
                    nomeLinha.text = motorista.carro?.linha!!.split(" - ")[1]
                    numeroLinha.text = motorista.carro?.linha!!.split(" - ")[0]
                    modelo_onibus.text = motorista.carro.modelo
                    numero_placa.text = motorista.carro.placa

                    nome_completo.text = motorista.linha!!.fiscal
                    telefone_pessoa.text = motorista.linha!!.fiscalNumero
                } catch (t: Throwable) {
                    Toast.makeText(
                        activity,
                        getString(R.string.erro_ao_carregar),
                        Toast.LENGTH_SHORT
                    ).show()
                }

                loading.value = false
            }

        })
    }

    fun viagemAtual(id: Int, token: String){
        val requests: MotoristaApi = HttpHelper().getApiClient()!!.create(MotoristaApi::class.java)

        val resultado = requests.consultarViagemAtualOuProxima(id, token)

        resultado.enqueue(object : Callback<Viagens>{
            override fun onFailure(call: Call<Viagens>, t: Throwable) {
                card_viagem.visibility = View.GONE
                println("erro na viagem atual: ${t.message}")
            }

            override fun onResponse(
                call: Call<Viagens>,
                response: Response<Viagens>
            ) {
                try {
                    val viagem = response.body()
                    card_viagem.visibility = View.VISIBLE
                    horario_inicial.text = viagem!!.horario
                    duracao_prevista.text = viagem.duracao
                }catch (t: Throwable){
                    card_viagem.visibility = View.GONE
//                    Toast.makeText(
//                        activity,
//                        getString(R.string.erro_ao_carregar),
//                        Toast.LENGTH_SHORT
//                    ).show()
                }
                println("erro na viagem atual: ${response.code()}")
                loading.value = false
            }
        })
    }
}