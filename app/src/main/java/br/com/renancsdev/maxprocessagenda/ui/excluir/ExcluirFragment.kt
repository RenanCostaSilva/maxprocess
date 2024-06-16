package br.com.renancsdev.maxprocessagenda.ui.excluir

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.renancsdev.maxprocessagenda.databinding.FragmentExcluirBinding
import br.com.renancsdev.maxprocessagenda.databinding.LayoutAlertExcluirBinding
import br.com.renancsdev.maxprocessagenda.extension.adapterRegioesSimples
import br.com.renancsdev.maxprocessagenda.extension.esconder
import br.com.renancsdev.maxprocessagenda.extension.mostrar
import br.com.renancsdev.maxprocessagenda.extension.nomesClientes
import br.com.renancsdev.maxprocessagenda.extension.setarRegiaoNome
import br.com.renancsdev.maxprocessagenda.model.Cliente
import java.util.stream.Collectors


class ExcluirFragment : Fragment() {

    private var _binding: FragmentExcluirBinding? = null

    private val binding get() = _binding!!
    private var nomeQuery: String = ""
    private lateinit var viewModel: ExcluirViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentExcluirBinding.inflate(inflater, container, false)

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
        viewModel = ViewModelProvider(this)[ExcluirViewModel::class.java]
        binding.viewModelExcluir = viewModel
        binding.lifecycleOwner = this
    }

    private fun esconderComponentes() {
        binding.cardListarClientesNaoEncontrados.esconder()
    }

    private fun verificarRegistro() {
        viewModel.total.observe(viewLifecycleOwner){
            if(it == 0){
                binding.cardExcluirCliente.esconder()
                binding.cardListarClientesNaoEncontrados.mostrar()
            }else{
                binding.cardExcluirCliente.mostrar()
                binding.cardListarClientesNaoEncontrados.esconder()

                carregarDadosNoSpinner()
                setarSpinnerDePesquisa()
                carregarDadosNoSpinerDeNomes()
                eventoBotoes()
            }
        }
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
            alertaExcluir(" Deseja excluir o cliente ? ")
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


    private fun deletar() {
        viewModel.excluirCliente(nomeQuery)
        viewModel.cliente.observe(viewLifecycleOwner) {
            if (it != null) {
                carregarDadosEncontradosDeCliente(it)
            }
        }
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

    private fun alertaExcluir(mesage: String) {
        val binding = LayoutAlertExcluirBinding.inflate(layoutInflater)
        val dialog = Dialog(binding.root.context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialog.setCancelable(false)
        dialog.setContentView(binding.root)

        binding.tvMsgDelete.text = mesage
        binding.btnFecharAlert.setOnClickListener {
            dialog.dismiss()
        }
        binding.btnExcluirAlert.setOnClickListener {
            deletar()
            verificarRegistro()
            dialog.dismiss()
        }
        dialog.show()
    }

}