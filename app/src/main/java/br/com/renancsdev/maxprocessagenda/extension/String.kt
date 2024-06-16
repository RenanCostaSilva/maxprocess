package br.com.renancsdev.maxprocessagenda.extension

import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.Calendar

fun String.seTextoEhValido(): Boolean{
    return this != "" && this.length >= 3
}

fun String.dataHoraPTBR(): String{
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss")
    return formatter.format(time)
}

fun String.seMenordeIdade(): Boolean{

    // 0 = são iguais / 1 depois = depois de 2006 / - 1 antes
    val strDataInicial = "$this"
    val strDataFinal = "31/12/2006"

    val sdf1 = SimpleDateFormat("dd/MM/yyyy")

    val dataFinal = sdf1.parse(strDataFinal)
    val dataInicial = sdf1.parse(strDataInicial)


    return dataInicial.compareTo(dataFinal) == 1
}