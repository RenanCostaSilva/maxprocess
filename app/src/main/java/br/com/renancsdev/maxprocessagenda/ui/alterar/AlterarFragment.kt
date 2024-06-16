package br.com.renancsdev.maxprocessagenda.ui.alterar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.renancsdev.maxprocessagenda.databinding.FragmentAlterarBinding
import br.com.renancsdev.maxprocessagenda.extension.adapterRegioesSimples
import br.com.renancsdev.maxprocessagenda.extension.esconder
import br.com.renancsdev.maxprocessagenda.extension.mostrar
import br.com.renancsdev.maxprocessagenda.extension.nomesClientes
import br.com.renancsdev.maxprocessagenda.extension.setarRegiaoNome
import br.com.renancsdev.maxprocessagenda.model.Cliente
import java.util.stream.Collectors


class AlterarFragment : Fragment() {

    private var _binding: FragmentAlterarBinding? = null
    private val binding get() = _binding!!
    private var nomeQuery: String = ""
    private lateinit var viewModel: AlterarViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlterarBinding.inflate(inflater, container, false)

        esconderComponentes()
        iniciarViewModel()
        verificarRegistro()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        verificarRegistro()
    }

    private fun iniciarViewModel() {
        viewModel = ViewModelProvider(this)[AlterarViewModel::class.java]
        binding.viewModelAlterar = viewModel
        binding.lifecycleOwner = this
    }

    private fun verificarRegistro(){
        viewModel.total.observe(viewLifecycleOwner){
            if(it == 0){
                binding.cardAlterarCliente.esconder()
                binding.cardListarClientesNaoEncontrados.mostrar()
            }else{
                binding.cardAlterarCliente.mostrar()
                binding.cardListarClientesNaoEncontrados.esconder()

                carregarDadosNoSpinner()
                setarSpinnerDePesquisa()
                carregarDadosNoSpinerDeNomes()
                eventoBotoes()
            }
        }

    }

    private fun esconderComponentes() {
        binding.cardListarClientesNaoEncontrados.esconder()
    }

    private fun carregarDadosNoSpinerDeNomes() {
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {/* sem uso */
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                carregarDados(parent!!.selectedItem.toString())
            }
        }
    }

    private fun eventoBotoes() {
        binding.btnAlterar.setOnClickListener {
            update()
        }
    }

    private fun setarSpinnerDePesquisa() {
        viewModel.registros.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                val lista: List<String> = it.stream()
                    .map(Cliente::nome)
                    .collect(Collectors.toList())
                binding.spinner.nomesClientes(binding.root.context, lista)
            }
        }
    }

    private fun carregarDados(nome: String) {
        viewModel.buscarClienteSelecionado(nome)
        viewModel.cliente.observe(viewLifecycleOwner) {
            if (it != null) {
                nomeQuery = it.nome
                carregarDadosEncontradosDeCliente(it)
            }
        }
    }

    private fun carregarDadosNoSpinner() {
        binding.spinUfUpdate.adapterRegioesSimples(binding.root.context)
        binding.spinUfUpdate.setSelection(1)
    }

    private fun update() {
        viewModel.alterarCliente(criarCliente(), nomeQuery)
        viewModel.cliente.observe(viewLifecycleOwner) {
            if (it != null) {
                carregarDadosEncontradosDeCliente(it)
            }
        }

    }

    private fun criarCliente(): Cliente {
        return Cliente(
            binding.editNome.text.toString(),
            binding.editCpf.text.toString(),
            binding.editDataNascimento.text.toString(),
            binding.editDataCadastro.text.toString(),
            binding.editTelefoneCelular.text.toString(),
            binding.editTelefoneFixo.text.toString(),
            binding.spinUfUpdate.selectedItem.toString()
        )
    }

    private fun carregarDadosEncontradosDeCliente(cliente: Cliente) {
        binding.editNome.setText(cliente.nome)
        binding.editCpf.setText(cliente.cpf)
        binding.editDataCadastro.setText(cliente.dataCadastro)
        binding.editDataNascimento.setText(cliente.dataNascimento)
        binding.editTelefoneCelular.setText(cliente.telefoneCelular)
        binding.editTelefoneFixo.setText(cliente.telefoneFixo)
        binding.spinUfUpdate.setarRegiaoNome(cliente.uf)
    }

}