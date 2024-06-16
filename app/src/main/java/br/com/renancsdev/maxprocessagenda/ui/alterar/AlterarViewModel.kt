package br.com.renancsdev.maxprocessagenda.ui.alterar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.renancsdev.maxprocessagenda.db.DBMaxProcess
import br.com.renancsdev.maxprocessagenda.extension.mensagemToast
import br.com.renancsdev.maxprocessagenda.model.Cliente
import br.com.renancsdev.maxprocessagenda.repository.AlterarRepository
import br.com.renancsdev.maxprocessagenda.repository.ListarRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AlterarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: AlterarRepository
    private val appli = getApplication() as Application

    private var _cliente = MutableLiveData<Cliente>()
    var cliente: LiveData<Cliente> = _cliente

    var registros: LiveData<List<Cliente>>
    var total: LiveData<Int>


    init{
        val clienteDao = DBMaxProcess.getInstance(application).clienteDao()
        repository = AlterarRepository(clienteDao)
        registros = buscarRegistros()
        total = buscarTotalDeRegistros()
        buscarClienteSelecionado("")
        buscarClienteSelecionado("")
    }

    private fun buscarTotalDeRegistros(): LiveData<Int> {
        return repository.buscarTotalDeRegistros()
    }

    fun buscarClienteSelecionado(nome: String){
        cliente = repository.buscarClientePorNome(nome)
        _cliente.value = cliente.value
    }

    private fun buscarRegistros(): LiveData<List<Cliente>> {
        return repository.buscarTodosRegistros()
    }

    fun alterarCliente(cliente: Cliente , nomeQuery: String) {
        viewModelScope.launch{

            withContext(IO){
                repository.alterarClientePorID(repository.buscarIDPorNome(nomeQuery), cliente.nome, cliente.cpf, cliente.dataNascimento, cliente.dataCadastro, cliente.telefoneCelular, cliente.telefoneFixo, cliente.uf)
                withContext(Main){
                    appli.mensagemToast("Cliente alterado com sucesso !")
                }

            }

        }
    }
}