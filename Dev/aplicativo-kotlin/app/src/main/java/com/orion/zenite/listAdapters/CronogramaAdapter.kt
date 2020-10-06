package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.Cronograma
import kotlinx.android.synthetic.main.list_item_cronograma.view.*

class CronogramaAdapter (var list: List<Cronograma>) :
    RecyclerView.Adapter<CronogramaAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)=
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_cronograma, parent, false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val antigo_tv = view.horario_antigo
        private val atual_tv = view.horario_atual
        private val motorista_tv = view.motorista_nome
        private val status_tv = view.status
        fun bind(cronograma: Cronograma) {
            antigo_tv.text = cronograma.horarioAntigo
            atual_tv.text = cronograma.horario
            motorista_tv.text = cronograma.nomeMotorista
            status_tv.text = cronograma.status
        }
    }
}