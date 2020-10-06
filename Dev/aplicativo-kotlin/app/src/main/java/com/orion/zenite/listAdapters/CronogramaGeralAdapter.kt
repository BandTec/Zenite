package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.fiscal.dashboard.Linhas
import com.orion.zenite.model.CronogramaGeral
import com.orion.zenite.model.HistoricoViagens
import kotlinx.android.synthetic.main.list_item_cronograma_parent.view.*
import kotlinx.android.synthetic.main.list_item_historico_parent.view.*
import kotlinx.android.synthetic.main.list_item_historico_parent.view.listViagens

class CronogramaGeralAdapter (var list: List<CronogramaGeral>) :
    RecyclerView.Adapter<CronogramaGeralAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)= ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_cronograma_parent, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = list[position]
        holder.textView.text = parent.nomeLinha

        val childLayoutManager = LinearLayoutManager(
            holder.recyclerView.context, RecyclerView.VERTICAL, false)
        childLayoutManager.initialPrefetchItemCount = 4

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter =
                CronogramaAdapter(parent.cronograma)
            setRecycledViewPool(viewPool)
        }
    }

     class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView : RecyclerView = itemView.listViagens
        val textView: TextView = itemView.nome_linha
    }
}