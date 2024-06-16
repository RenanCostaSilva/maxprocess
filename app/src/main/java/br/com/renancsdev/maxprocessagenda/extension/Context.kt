package br.com.renancsdev.maxprocessagenda.extension

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Toast
import br.com.renancsdev.maxprocessagenda.databinding.LayoutAlertBinding

fun Context.mensagemToast(mensagem: String){
    Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show()
}

fun Context.alerta(mesage: String , layoutInflater: LayoutInflater){
    val binding = LayoutAlertBinding.inflate(layoutInflater)
    val dialog = Dialog(binding.root.context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    dialog.setCancelable(false)
    dialog.setContentView(binding.root)

    binding.tvMsgDelete.text = mesage
    binding.btnOkAlert.setOnClickListener {
        dialog.dismiss()
    }
    dialog.show()
}
