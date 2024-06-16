package br.com.renancsdev.maxprocessagenda.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.maxprocessagenda.adapter.viewholder.ClienteViewHolderExcluir
import br.com.renancsdev.maxprocessagenda.databinding.LayoutItemAgendaExcluirBinding
import br.com.renancsdev.maxprocessagenda.model.Cliente

class RecyclerViewClienteExcluir(private var listaCliente: List<Cliente>): RecyclerView.Adapter<ClienteViewHolderExcluir>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ClienteViewHolderExcluir(
        LayoutItemAgendaExcluirBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount() = listaCliente.size
    override fun onBindViewHolder(holder: ClienteViewHolderExcluir, position: Int) = holder.bind(listaCliente[position])

}