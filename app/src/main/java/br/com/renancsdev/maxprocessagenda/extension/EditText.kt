package br.com.renancsdev.maxprocessagenda.extension

import android.widget.EditText
import br.com.renancsdev.mascara.mask.Mask
import java.text.SimpleDateFormat
import java.util.Calendar

fun EditText.dataHoraPTBR(){
    val time = Calendar.getInstance().time
    val formatter = SimpleDateFormat("dd/MM/yyyy - HH:mm:ss")
    this.setText(formatter.format(time))
}

fun EditText.limparCampo(){
    this.setText("")
}

fun EditText.setarMascara(formato: String){
    this.addTextChangedListener(Mask.mask(this , formato))
}

fun EditText.seMenordeIdade(): Boolean{

    // 0 = são iguais / 1 depois = depois de 2006 / - 1 antes
    val strDataInicial = "${this.text} 00:00"
    val strDataFinal = "31/12/2006 00:00"

    val sdf1 = SimpleDateFormat("dd/MM/yyyy HH:mm")

    val dataFinal = sdf1.parse(strDataFinal)
    val dataInicial = sdf1.parse(strDataInicial)


    return dataInicial.compareTo(dataFinal) == 1
}

fun EditText.seTextoEhValido(): Boolean{
    return this.text.toString().isNotEmpty()
}