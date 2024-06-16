package br.com.renancsdev.maxprocessagenda.repository

import androidx.lifecycle.LiveData
import br.com.renancsdev.maxprocessagenda.dao.ClienteDao
import br.com.renancsdev.maxprocessagenda.model.Cliente

class AlterarRepository(private val clienteDao: ClienteDao) {

    fun alterarClientePorID(id: Int, nome: String, cpf: String, dataNascimento: String, dataCadastro: String, telefone: String, telefoneFixo: String, uf: String) = clienteDao.updateClientePorID(id , nome, cpf, dataNascimento, dataCadastro, telefone, telefoneFixo, uf)
    fun buscarIDPorNome(nome: String): Int = clienteDao.buscarClienteIDPorNome(nome)
    fun buscarClientePorNome(nome: String): LiveData<Cliente> = clienteDao.buscarClientePorNomeLive(nome)
    fun buscarClientePorCPF(cpf: String): LiveData<Cliente> = clienteDao.buscarClientePorCPF(cpf)
    fun buscarTodosRegistros(): LiveData<List<Cliente>> = clienteDao.buscarTodosRegistrosLiveData()
    fun buscarTotalDeRegistros(): LiveData<Int> = clienteDao.buscarTotalRegistros2()

}