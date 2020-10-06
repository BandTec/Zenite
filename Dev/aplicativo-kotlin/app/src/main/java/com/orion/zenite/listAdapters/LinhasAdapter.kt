package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import kotlinx.android.synthetic.main.list_item_linhas.view.*

class LinhasAdapter (var list: List<String>) :
    RecyclerView.Adapter<LinhasAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)=
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_linhas, parent, false)
        )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val linha_tv = view.nome_linha
        fun bind(nome_linha: String) {
            linha_tv.text = nome_linha
        }
    }
}