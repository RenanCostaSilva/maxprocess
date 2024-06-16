package br.com.renancsdev.maxprocessagenda.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.maxprocessagenda.adapter.viewholder.ClienteViewHolder
import br.com.renancsdev.maxprocessagenda.databinding.LayoutItemAgendaBinding
import br.com.renancsdev.maxprocessagenda.model.Cliente

class RecyclerViewCliente(private var listaCliente: List<Cliente>): RecyclerView.Adapter<ClienteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ClienteViewHolder(LayoutItemAgendaBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount() = listaCliente.size
    override fun onBindViewHolder(holder: ClienteViewHolder, position: Int) = holder.bind(listaCliente[position])

}