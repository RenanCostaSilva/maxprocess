package br.com.renancsdev.maxprocessagenda.ui.listar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import br.com.renancsdev.maxprocessagenda.db.DBMaxProcess
import br.com.renancsdev.maxprocessagenda.extension.mensagemToast
import br.com.renancsdev.maxprocessagenda.model.Cliente
import br.com.renancsdev.maxprocessagenda.repository.ListarRepository
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListarViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: ListarRepository
    private val appli = getApplication() as Application
    var registros: LiveData<List<Cliente>>
    var total: LiveData<Int>


    init {
        val clienteDao = DBMaxProcess.getInstance(application).clienteDao()
        repository = ListarRepository(clienteDao)
        registros = buscarRegistros()
        total = buscarTotalRegistros()
        buscarTotalRegistros()
    }

    private fun buscarTotalRegistros(): LiveData<Int> {
        return repository.buscarTotalDeRegistros()
    }

    private fun buscarRegistros(): LiveData<List<Cliente>> {
        return repository.buscarTodosRegistros()
    }

}