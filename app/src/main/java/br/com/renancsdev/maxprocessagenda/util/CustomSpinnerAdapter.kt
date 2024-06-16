package br.com.renancsdev.maxprocessagenda.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import br.com.renancsdev.maxprocessagenda.R

class CustomSpinnerAdapter (context: Context, items: List<String>) : ArrayAdapter<String>(context, 0, items){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return createItemView(position, convertView, parent)
    }

    private fun createItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.view_drop_down_menu, parent, false)

        val item = getItem(position)
        val textView: TextView = view.findViewById(R.id.txtDropDownLabel)
        textView.text = item

        return view
    }

}