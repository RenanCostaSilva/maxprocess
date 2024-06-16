package br.com.renancsdev.maxprocessagenda.util

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import br.com.renancsdev.maxprocessagenda.R

class CustomDropDownAdapter(context: Context, var listItemsTxt: Array<String>): BaseAdapter() {

    val mInflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
       return listItemsTxt.size
    }

    override fun getItem(position: Int): Any {
        return listItemsTxt[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val vh: ItemRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.view_drop_down_menu, parent, false)
            vh = ItemRowHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ItemRowHolder
        }
        val params = view.layoutParams
        params.height = 60
        view.layoutParams = params

        vh.label.text = listItemsTxt[position]

        return view

    }

    private class ItemRowHolder(row: View?) {
        val label: TextView = row?.findViewById(R.id.txtDropDownLabel)!!
    }

}