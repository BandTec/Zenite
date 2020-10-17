package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.Linha
import kotlinx.android.synthetic.main.list_item_linhas.view.*

class LinhasAdapter (var list: ArrayList<Linha>, var clickListener: (Linha) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

    fun update(new: List<Linha>) {
        list.clear()
        list.addAll(new)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)=
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_linhas, parent, false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position], clickListener)
//        holder.itemView.setOnClickListener{
//            clickListener(list[position])
//        }f
    }
}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val linhaIda_tv = view.nomeIda_linha
    private val linhaVolta_tv = view.nomeVolta_linha
    private val numero_tv = view.numero_linha
    private val card = view.card

    fun bind(linha: Linha, clickListener: (Linha) -> Unit) {

        numero_tv.text =  linha.numero
        linhaIda_tv.text =  linha.pontoIda.nome
        linhaVolta_tv.text =  linha.pontoVolta.nome

        card.setOnClickListener{
            clickListener(linha)
        }
    }
}
