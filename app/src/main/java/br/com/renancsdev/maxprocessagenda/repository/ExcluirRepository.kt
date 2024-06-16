package br.com.renancsdev.maxprocessagenda.repository

import androidx.lifecycle.LiveData
import br.com.renancsdev.maxprocessagenda.dao.ClienteDao
import br.com.renancsdev.maxprocessagenda.model.Cliente

class ExcluirRepository(private val clienteDao: ClienteDao) {

    fun deletarClientePorID(id: Int) = clienteDao.deleteCLientePorNome2(id)
    fun buscarIDPorNome(nome: String): Int = clienteDao.buscarClienteIDPorNome(nome)
    fun buscarClientePorNome(nome: String): LiveData<Cliente> = clienteDao.buscarClientePorNomeLive(nome)
    fun buscarTodosRegistros(): LiveData<List<Cliente>> = clienteDao.buscarTodosRegistrosLiveData()
    fun buscarTotalDeRegistros(): LiveData<Int> = clienteDao.buscarTotalRegistros2()

}