package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.Linha
import com.orion.zenite.model.MotoristaOnibus
import com.orion.zenite.model.Onibus
import kotlinx.android.synthetic.main.list_item_motorista_onibus.view.*

class MotoristaOnibusAdapter  (var list: ArrayList<Onibus>) :
    RecyclerView.Adapter<MotoristaOnibusAdapter.ViewHolder>() {

    fun update(new: List<Onibus>) {
        list.clear()
        list.addAll(new)
        notifyDataSetChanged()
    }

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

        fun bind(item: Onibus) {
            nome_motorista_tv.text = item.motorista
            telefone_tv.text = item.motoristaTelefone
            onibus_tv.text = item.placa
        }
    }
}