package br.com.renancsdev.maxprocessagenda.ui.cadastrar

import android.app.Application
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.renancsdev.maxprocessagenda.R
import br.com.renancsdev.maxprocessagenda.db.DBMaxProcess
import br.com.renancsdev.maxprocessagenda.extension.mensagemToast
import br.com.renancsdev.maxprocessagenda.extension.seMenordeIdade
import br.com.renancsdev.maxprocessagenda.model.Cliente
import br.com.renancsdev.maxprocessagenda.repository.CadastroRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.Calendar
import java.util.Locale

class CadastroViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CadastroRepository
    private val appli = getApplication() as Application

    var nome = MutableLiveData<String>()
    var cpf = MutableLiveData<String>()
    var dataCadastro = MutableLiveData<String>()
    var dataNascimento = MutableLiveData<String>()
    var telefoneCelular = MutableLiveData<String>()
    var telefoneFixo = MutableLiveData<String>()
    var uf = ObservableArrayList<String>()
    var selected = MutableLiveData<String>()
    val spinnerItems = MutableLiveData<List<String>>()

    init {
        val clienteDao = DBMaxProcess.getInstance(application).clienteDao()
        repository = CadastroRepository(clienteDao)

        // seta os valores iniciais
        nome.value = ""
        cpf.value = ""
        dataCadastro.value = dataHoraPTBR()
        dataNascimento.value = dataNascimentoValidaParaCadastro()
        telefoneCelular.value = "(31) 98765-1234"
        telefoneFixo.value = "(31) 3462-7894"
        spinnerItems.value = appli.resources.getStringArray(R.array.regioes_tipo).toList()
    }

    private fun criarCliente(): Cliente {
        return Cliente(
            nome.value!!,
            cpf.value!!,
            dataCadastro.value!!,
            dataNascimento.value!!,
            selected.value!!,
            telefoneCelular.value!!,
            telefoneFixo.value!!
        )
    }

    fun salvarClienteNoBanco() {

        viewModelScope.launch {

            withContext(IO) {
                if (repository.verificarSeExisteMesmoNomeAntesDeSalvar(nome.value!!) == 1 && repository.verificarSeExisteMesmoCpfAntesDeSalvar(cpf.value!!) == 1 &&
                    repository.verificarSeExisteMesmoCelularAntesDeSalvar(telefoneCelular.value!!) == 1) {
                    withContext(Main) {
                        appli.mensagemToast("Cliente já cadastrado !")
                    }
                } else {
                    repository.inserir(criarCliente())
                    withContext(Main) {
                        appli.mensagemToast("Cliente cadastrado com sucesso !")
                        limparDados()
                    }

                }
            }

        }
    }

    private fun limparDados() {
        nome.value = ""
        cpf.value = ""
        dataCadastro.value = ""
        dataNascimento.value = ""
        telefoneCelular.value = ""
        telefoneFixo.value = ""
    }

    fun onItemSelected(position: Int) {
        selected.value = spinnerItems.value!![position]
    }

    fun atualizarCPFParaSaoPaulo() {
        cpf.value = "615.973.040-18"
    }

    fun dataNascimentoValidaParaCadastro(): String {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -18) // Subtract one year
        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return formatter.format(calendar.time)
    }

    fun vericarRegistroParaMGSeEhMenorIdade() {
        if (dataNascimento.value != "" && dataNascimento.value!!.seMenordeIdade()){
            dataNascimento.value = ""
            appli.mensagemToast("Menores de idade não podem ser cadastrados !")
        }
    }

    private fun dataHoraPTBR(): String{
        val time = Calendar.getInstance().time
        val formatter = SimpleDateFormat("dd/MM/yyyy")
        return formatter.format(time)
    }

}