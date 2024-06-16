package br.com.renancsdev.maxprocessagenda.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Cliente(


    @ColumnInfo(name = "NOME")
    var nome:String = "",

    @ColumnInfo(name = "CPF")
    var cpf: String = "",

    @ColumnInfo(name = "DATACADASTRO")
    var dataCadastro: String = "",

    @ColumnInfo(name = "DATANASCIMENTO")
    var dataNascimento: String = "",

    @ColumnInfo(name = "UF")
    var uf: String = "",

    @ColumnInfo(name = "TELEFONECELULAR")
    var telefoneCelular: String = "",

    @ColumnInfo(name = "TELEFONEFIXO")
    var telefoneFixo: String = "",
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
