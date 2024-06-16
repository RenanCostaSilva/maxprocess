package br.com.renancsdev.maxprocessagenda.repository

import androidx.lifecycle.LiveData
import br.com.renancsdev.maxprocessagenda.dao.ClienteDao
import br.com.renancsdev.maxprocessagenda.model.Cliente

class ListarRepository(private val clienteDao: ClienteDao) {

    fun buscarTodosRegistros()   : LiveData<List<Cliente>>  = clienteDao.buscarTodosRegistrosLiveData()
    fun buscarTotalDeRegistros() : LiveData<Int>            = clienteDao.buscarTotalRegistros2()

}