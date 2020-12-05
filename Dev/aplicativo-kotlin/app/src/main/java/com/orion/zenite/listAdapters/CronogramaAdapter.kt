package com.orion.zenite.listAdapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.Cronograma
import kotlinx.android.synthetic.main.list_item_cronograma.view.*

// essa classe adapta uma lista de cronogramas
// adicionando seus itens corretamente ao layout do item criado em res/layout/list_item_cronograma
class CronogramaAdapter (var list: ArrayList<Cronograma>) :
    RecyclerView.Adapter<CronogramaAdapter.ViewHolder>() {

    fun update(new: List<Cronograma>) {
        list.clear()
        list.addAll(new)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)=
        // AQUI ALTERA O LAYOUT DO ITEM DA LISTA
        // NO CASO list_item_cronograma
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_cronograma, parent, false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        // AQUI SALVA OS ITENS DO LAYOUT DO ITEM DA LISTA EM UMA VARIAVEL
        private val antigo_tv = view.horario_antigo
        private val atual_tv = view.horario_atual
        private val motorista_tv = view.motorista_nome
        private val status_tv = view.status
        private val chegada_tv = view.chegada

        fun bind(cronograma: Cronograma) {
            // NESTE PONTO ADICIONA OS DADOS DA CLASSE AOS ITENS DO LAYOUT
            antigo_tv.text = cronograma.horarioAntigo
            atual_tv.text = cronograma.horarioPrevisto
            motorista_tv.text = cronograma.nomeMotorista.split(" ")[0]


            if(cronograma.horarioAntigo.isEmpty()) {
                antigo_tv.visibility = View.GONE
            }else {
                antigo_tv.visibility = View.VISIBLE
            }

            chegada_tv.text = cronograma.horarioRealizado
            chegada_tv.visibility = View.VISIBLE
            if(!cronograma.horarioRealizado.isEmpty()){
                if(cronograma.atrasado){
                    chegada_tv.setTextColor(Color.RED)
                } else {
                    chegada_tv.setTextColor(Color.BLACK)
                }
            }
        }
    }
}