package br.com.renancsdev.maxprocessagenda.ui.cadastrar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import br.com.renancsdev.mascara.mask.Mask
import br.com.renancsdev.maxprocessagenda.R
import br.com.renancsdev.maxprocessagenda.databinding.FragmentCadastroBinding
import br.com.renancsdev.maxprocessagenda.extension.alerta
import br.com.renancsdev.maxprocessagenda.extension.limparCampo
import br.com.renancsdev.maxprocessagenda.extension.seTextoEhValido
import br.com.renancsdev.maxprocessagenda.extension.setarMascara

class CadastroFragment : Fragment() {

    private var _binding: FragmentCadastroBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCadastroBinding.inflate(inflater, container, false)

        iniciarViewModel()
        configurarMascaras()

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        binding.spinRegiao.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {/* sem uso */
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if (parent!!.selectedItem == "SP") {

                    binding.editCpf.limparCampo()
                    viewModel.cpf.observe(binding.lifecycleOwner!!) { newCpf ->
                        binding.editCpf.setText(newCpf)
                    }
                    viewModel.atualizarCPFParaSaoPaulo()

                } else if (parent.selectedItem == "MG") {
                    viewModel.dataNascimento.observe(binding.lifecycleOwner!!) { newDtNascimento ->
                        binding.editDataNascimento.setText(newDtNascimento)
                    }
                    viewModel.vericarRegistroParaMGSeEhMenorIdade()
                }
            }
        }
        binding.btnCadastro.setOnClickListener {
            checkCadastro()
        }
    }

    private fun iniciarViewModel() {
        viewModel = ViewModelProvider(this)[CadastroViewModel::class.java]
        binding.viewModelCadastro = viewModel
        binding.lifecycleOwner = this
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun checkCadastro(): Boolean {
        return if (binding.editNome.seTextoEhValido() && binding.editCpf.seTextoEhValido() && binding.editDataNascimento.seTextoEhValido()
            && binding.editTelefoneFixo.seTextoEhValido() && binding.editTelefoneCelular.seTextoEhValido() && binding.spinRegiao.selectedItem != "Selecione uma Região"
        ) {
            viewModel.nome.value = binding.editNome.text.toString()
            viewModel.cpf.value = binding.editCpf.text.toString()
            viewModel.dataNascimento.value = binding.editDataNascimento.text.toString()
            viewModel.dataCadastro.value = binding.editDataCadastro.text.toString()
            viewModel.telefoneCelular.value = binding.editTelefoneCelular.text.toString()
            viewModel.telefoneFixo.value = binding.editTelefoneFixo.text.toString()
            viewModel.spinnerItems.value = resources.getStringArray(R.array.regioes_tipo).toList()
            viewModel.selected.value = binding.spinRegiao.selectedItem.toString()
            viewModel.salvarClienteNoBanco()
            limparCampos()
            true
        } else {
            when {
                !binding.editNome.seTextoEhValido() -> {
                    binding.root.context.alerta("Nome inválido", layoutInflater)
                }

                !binding.editCpf.seTextoEhValido() -> {
                    binding.root.context.alerta("CPF inválido", layoutInflater)
                }

                !binding.editDataNascimento.seTextoEhValido() -> {
                    binding.root.context.alerta("Data de Nascimento inválida", layoutInflater)
                }

                !binding.editTelefoneFixo.seTextoEhValido() -> {
                    binding.root.context.alerta("Telefone Fixo inválido", layoutInflater)
                }

                !binding.editTelefoneCelular.seTextoEhValido() -> {
                    binding.root.context.alerta("Telefone Celular inválido", layoutInflater)
                }

                binding.spinRegiao.selectedItem == "Selecione uma Região" -> {
                    binding.root.context.alerta("Região inválida", layoutInflater)
                }

            }
            false
        }
    }

    private fun limparCampos() {
        binding.editNome.limparCampo()
        binding.editCpf.limparCampo()
        binding.editDataNascimento.limparCampo()
        binding.editTelefoneFixo.limparCampo()
        binding.editTelefoneCelular.limparCampo()
        binding.spinRegiao.setSelection(0)
    }

    private fun configurarMascaras() {

        binding.editCpf.setarMascara(Mask.FORMATO_CPF)
        binding.editDataCadastro.setarMascara(Mask.FORMATO_DATE_HOUR)
        binding.editDataNascimento.setarMascara(Mask.FORMATO_DATE)
        binding.editTelefoneCelular.setarMascara(Mask.FORMATO_FONE_CELULAR_DDD)
        binding.editTelefoneFixo.setarMascara(Mask.FORMATO_FONE_FIXO_DDD)

    }

}