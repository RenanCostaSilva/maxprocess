package br.com.renancsdev.maxprocessagenda.extension

import android.content.Context
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import br.com.renancsdev.mascara.mask.Mask
import br.com.renancsdev.maxprocessagenda.R
import br.com.renancsdev.maxprocessagenda.util.CustomDropDownAdapter
val CPF: String = "615.973.040-18"

fun Spinner.adapterRegioes(context: Context){
    this.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item , resources.getStringArray(R.array.regioes_tipo))
    this.adapter = adapter
}

fun Spinner.adapterRegioesSimples(context: Context){
    val adapter = ArrayAdapter(context , android.R.layout.simple_spinner_item, resources.getStringArray(R.array.regioes_tipo_simples))
    this.adapter = adapter
}

fun Spinner.nomesClientes(context: Context , listagem: List<String>){
    val adapter = ArrayAdapter(context , android.R.layout.simple_spinner_item, listagem)
    this.adapter = adapter
}

fun Spinner.setarRegiaoNome(uf: String){
    this.setSelection(resources.getStringArray(R.array.regioes_tipo_simples).indexOf(uf))
}

fun Spinner.selecionarRegiaoCadastro(cpf: EditText , dataNascimento: EditText){
    this.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
        override fun onNothingSelected(parent: AdapterView<*>?) {/* sem uso */}

        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            if (position != 0){
                if(parent!!.selectedItem == "SP"){
                    cpf.limparCampo()
                    cpf.setText(CPF)
                }
                else if(parent.selectedItem == "MG"){
                    if(dataNascimento.seMenordeIdade()){
                        dataNascimento.limparCampo()
                        dataNascimento.context.mensagemToast("Menores de idade não podem ser cadastrados")
                    }
                }
            }
        }
    }
}
