package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.MotoristaOnibus
import kotlinx.android.synthetic.main.list_item_motorista_onibus.view.*

class MotoristaOnibusAdapter  (var list: List<MotoristaOnibus>) :
    RecyclerView.Adapter<MotoristaOnibusAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)=
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_motorista_onibus, parent, false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val nome_motorista_tv = view.nome_motorista
        private val telefone_tv = view.telefone
        private val onibus_tv = view.onibus

        fun bind(viagem: MotoristaOnibus) {
            nome_motorista_tv.text = viagem.motoristaNome
            telefone_tv.text = viagem.telefone
            onibus_tv.text = viagem.placaOnibus
        }
    }
}