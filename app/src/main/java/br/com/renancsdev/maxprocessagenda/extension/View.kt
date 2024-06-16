package br.com.renancsdev.maxprocessagenda.extension

import android.view.View

fun View.esconder(){
    this.visibility = View.GONE
}

fun View.mostrar(){
    this.visibility = View.VISIBLE
}