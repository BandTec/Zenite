package com.orion.zenite.listAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.orion.zenite.R
import com.orion.zenite.model.CronogramaGeral
import com.orion.zenite.model.Linha
import kotlinx.android.synthetic.main.list_item_cronograma_parent.view.*
import kotlinx.android.synthetic.main.list_item_historico_parent.view.listViagens

// essa classe adapta uma lista de cronograma de linhas
// neste caso essa lista tem como item outras listas
// e esta classe recebe uma função para ser utilizada no clique de cada item -> clickListener
class CronogramaGeralAdapter (var list: ArrayList<CronogramaGeral>, var clickListener: (CronogramaGeral) -> Unit) :
    RecyclerView.Adapter<CronogramaGeralAdapter.ViewHolder>() {

    fun update(new: List<CronogramaGeral>) {
        list.clear()
        list.addAll(new)
        notifyDataSetChanged()
    }

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int)= ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_item_cronograma_parent, parent, false)
    )

    override fun getItemCount(): Int {
        return list.size
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val parent = list[position]

        // passa a função clickListener para a classe que passa os dados de cada item para o layout
        holder.bind(parent, clickListener, viewPool)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nome_linha: TextView = view.nome_linha
        val btn: Button = view.botao_ver_tudo
        val lista: RecyclerView = view.listViagens

        fun bind(cronograma: CronogramaGeral, clickListener: (CronogramaGeral) -> Unit, viewPool: RecyclerView.RecycledViewPool) {
            nome_linha.text = cronograma.nomeLinha

            // acesso o btn que existe no layout e o conecto a função clickListener
            btn.setOnClickListener{
                clickListener(cronograma)
            }

            // passo a lista interna para seu próprio recycler view que utiliza outro adapter
            val childLayoutManager = LinearLayoutManager(
                lista.context, RecyclerView.VERTICAL, false)
            childLayoutManager.initialPrefetchItemCount = 4

            lista.apply {
                layoutManager = childLayoutManager
                adapter =
                    CronogramaAdapter(cronograma.cronograma)
                setRecycledViewPool(viewPool)
            }
        }
    }
}