package br.com.renancsdev.maxprocessagenda.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import br.com.renancsdev.maxprocessagenda.databinding.LayoutItemAgendaAlterarBinding
import br.com.renancsdev.maxprocessagenda.databinding.LayoutItemAgendaExcluirBinding
import br.com.renancsdev.maxprocessagenda.model.Cliente

class ClienteViewHolderExcluir(var binding: LayoutItemAgendaExcluirBinding): RecyclerView.ViewHolder(binding.root)  {

    fun bind(cliente: Cliente){
        binding.tvNomeValor.text = cliente.nome
        binding.tvUfText.text = cliente.uf
        binding.tvCpfText.text = cliente.cpf

        binding.tvDtRegistroLabelText.text = cliente.dataCadastro
        binding.tvDtNascimentoLabelText.text = cliente.dataNascimento
        binding.tvTelCelularLabelText.text = cliente.telefoneCelular
        binding.tvTelFixoLabelText.text = cliente.telefoneFixo

        binding.cardItemExcluir.setOnClickListener {

        }

    }

}