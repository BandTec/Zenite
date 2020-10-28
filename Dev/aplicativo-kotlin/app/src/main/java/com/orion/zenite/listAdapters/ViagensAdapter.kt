package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.Linha
import com.orion.zenite.model.Viagens
import kotlinx.android.synthetic.main.list_item_historico.view.*

class ViagensAdapter (var list: ArrayList<Viagens>) :
    RecyclerView.Adapter<ViagensAdapter.ViewHolder>() {

    fun update(new: List<Viagens>) {
        list.clear()
        list.addAll(new)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)=
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_historico, parent, false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val horario_tv = view.horario
        private val duracao_tv = view.duracao
        fun bind(viagem: Viagens) {
            horario_tv.text = viagem.horario
            duracao_tv.text = viagem.duracao
        }
    }
}