package br.com.renancsdev.maxprocessagenda.ui.listar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.renancsdev.maxprocessagenda.adapter.recyclerview.RecyclerViewCliente
import br.com.renancsdev.maxprocessagenda.databinding.FragmentListarBinding
import br.com.renancsdev.maxprocessagenda.extension.esconder
import br.com.renancsdev.maxprocessagenda.extension.mostrar
import br.com.renancsdev.maxprocessagenda.model.Cliente

class ListarFragment : Fragment() {

    private var _binding: FragmentListarBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ListarViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListarBinding.inflate(inflater, container, false)

        esconderComponentes()
        iniciarViewModel()
        verificarRegistro()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        verificarRegistro()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun iniciarViewModel() {
        viewModel = ViewModelProvider(this)[ListarViewModel::class.java]
        binding.viewModelListar = viewModel
        binding.lifecycleOwner = this
    }

    private fun verificarRegistro() {
        viewModel.total.observe(viewLifecycleOwner) {
            if (it == 0) {
                binding.cardListarClientes.esconder()
                binding.cardListarClientesNaoEncontrados.mostrar()
            } else {
                binding.cardListarClientes.mostrar()
                binding.cardListarClientesNaoEncontrados.esconder()
                carregarDados()
            }
        }
    }

    private fun esconderComponentes() {
        binding.cardListarClientesNaoEncontrados.esconder()
    }

    private fun carregarDados() {
        viewModel.registros.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                configurarAdapter(it)
            }
        }
    }

    private fun configurarAdapter(listaCliente: List<Cliente>) {
        binding.rvListaClientes.apply {
            layoutManager = LinearLayoutManager(binding.root.context)
            adapter = RecyclerViewCliente(listaCliente)
        }
    }

}