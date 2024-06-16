package br.com.renancsdev.maxprocessagenda.repository

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import br.com.renancsdev.maxprocessagenda.dao.ClienteDao
import br.com.renancsdev.maxprocessagenda.db.DBMaxProcess
import br.com.renancsdev.maxprocessagenda.model.Cliente
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CadastroRepository(private val clienteDao: ClienteDao) {
     fun inserir(cliente: Cliente) = clienteDao.insert(cliente)
     fun verificarSeExisteMesmoNomeAntesDeSalvar(nome: String): Int = clienteDao.checarSeClienteExisteComMesmoNome(nome)
     fun verificarSeExisteMesmoCpfAntesDeSalvar(cpf: String): Int = clienteDao.checarSeClienteExisteComMesmoCPF(cpf)
     fun verificarSeExisteMesmoCelularAntesDeSalvar(celular: String): Int = clienteDao.checarSeClienteExisteComMesmoTelCelular(celular)
}