package br.com.renancsdev.maxprocessagenda.adapter.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.maxprocessagenda.adapter.viewholder.ClienteViewHolderAlterar
import br.com.renancsdev.maxprocessagenda.databinding.LayoutItemAgendaAlterarBinding
import br.com.renancsdev.maxprocessagenda.model.Cliente

class RecyclerViewClienteAlterar(private var listaCliente: List<Cliente>): RecyclerView.Adapter<ClienteViewHolderAlterar>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ClienteViewHolderAlterar(LayoutItemAgendaAlterarBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    override fun getItemCount() = listaCliente.size
    override fun onBindViewHolder(holder: ClienteViewHolderAlterar, position: Int) = holder.bind(listaCliente[position])
}