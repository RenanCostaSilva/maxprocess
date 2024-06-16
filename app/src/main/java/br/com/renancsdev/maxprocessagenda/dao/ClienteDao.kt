package br.com.renancsdev.maxprocessagenda.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.renancsdev.maxprocessagenda.model.Cliente

@Dao
interface ClienteDao {

    // cadastro
    // insert
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cliente: Cliente)

    @Query("SELECT COUNT(1) id FROM Cliente where cpf = :cpf")
    fun checarSeClienteExisteComMesmoCPF(cpf: String): Int

    @Query("SELECT COUNT(1) id FROM Cliente where nome = :nome")
    fun checarSeClienteExisteComMesmoNome(nome: String): Int

    @Query("SELECT COUNT(1) id FROM Cliente where telefoneCelular = :celular")
    fun checarSeClienteExisteComMesmoTelCelular(celular: String): Int


    //listagem
    @Query("SELECT * FROM Cliente ")
    fun buscarTodosRegistrosLiveData(): LiveData<List<Cliente>>

    @Query("SELECT COUNT(*) FROM Cliente")
    suspend fun buscarTotalRegistros(): Int

    @Query("SELECT COUNT(*) FROM Cliente")
    fun buscarTotalRegistros2(): LiveData<Int>

    @Query("SELECT id FROM Cliente where nome = :nome")
    fun buscarClienteIDPorNome(nome: String): Int

    @Query("SELECT * FROM Cliente where nome = :nome")
    fun buscarClientePorNomeLive(nome: String): LiveData<Cliente>

    @Query("SELECT * FROM Cliente where cpf = :cpf")
    fun buscarClientePorCPF(cpf: String): LiveData<Cliente>

    //update
    @Query("UPDATE Cliente SET NOME = :nome, CPF = :cpf, DATANASCIMENTO = :dataNascimento , " +
            "DATACADASTRO = :dataCadastro ,TELEFONECELULAR = :telefone ,TELEFONEFIXO = :telefoneFixo , UF = :uf WHERE id LIKE :id")
    fun updateClientePorID(id: Int, nome: String, cpf: String, dataNascimento: String, dataCadastro: String, telefone: String, telefoneFixo: String, uf: String)



    //delete
    @Query("DELETE FROM Cliente WHERE id LIKE :id")
    fun deleteCLientePorNome2(id: Int)


}