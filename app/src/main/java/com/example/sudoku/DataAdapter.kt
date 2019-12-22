package com.example.sudoku

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

class DataAdapter(private var activity: Activity, private var list: List<Int>, private var firstlist: List<Int>) : BaseAdapter(){

    private class ViewHolder(cell: View?){
        var textCell: TextView? = null
        var layout: ConstraintLayout? = null
        init {
            this.textCell = cell?.findViewById<TextView>(R.id.textView_cell)
            this.layout = cell?.findViewById(R.id.cell_layout1)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val  view: View
        val viewHolder: ViewHolder
        if (convertView == null){
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.cell, null)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        }
        else{
            view = convertView
            viewHolder = view.tag as ViewHolder
        }
        val item = list[position]
        val firstitem = firstlist[position]

        if (firstitem == 0 && item == 0) {
            viewHolder.textCell?.text = " "
            viewHolder.layout?.setBackgroundColor(Color.LTGRAY)
        }
        else if(firstitem == 0 && item != 0){
            viewHolder.textCell?.text = item.toString()
            viewHolder.layout?.setBackgroundColor(Color.LTGRAY)
        }
        else viewHolder.textCell?.text = item.toString()

        return view
    }

    override fun getItem(position: Int): Int {
        return list[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return list.size
    }

}