package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import kotlinx.android.synthetic.main.list_item_linhas.view.*

class LinhasAdapter (var list: List<String>, var clickListener: (String) -> Unit) :
    RecyclerView.Adapter<ViewHolder>() {

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
    private val linha_tv = view.nome_linha
    private val card = view.card
    fun bind(nome_linha: String, clickListener: (String) -> Unit) {
        linha_tv.text = nome_linha

        card.setOnClickListener{
            clickListener(nome_linha)
        }
    }
}
