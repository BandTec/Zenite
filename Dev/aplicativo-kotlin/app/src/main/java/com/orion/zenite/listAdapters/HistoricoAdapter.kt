package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.HistoricoViagens
import kotlinx.android.synthetic.main.list_item_historico.view.duracao
import kotlinx.android.synthetic.main.list_item_historico.view.horario
import kotlinx.android.synthetic.main.list_item_historico_parent.view.*

class HistoricoAdapter (var list: List<HistoricoViagens>) :
    RecyclerView.Adapter<HistoricoAdapter.ViewHolder>() {

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)= ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_historico_parent, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = list[position]
        holder.textView.text = parent.data

        val childLayoutManager = LinearLayoutManager(
        holder.recyclerView.context, RecyclerView.VERTICAL, false)
        childLayoutManager.initialPrefetchItemCount = 4

        holder.recyclerView.apply {
            layoutManager = childLayoutManager
            adapter =
                ViagensAdapter(parent.viagens)
            setRecycledViewPool(viewPool)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView : RecyclerView = itemView.listViagens
        val textView: TextView = itemView.titulo
    }
}