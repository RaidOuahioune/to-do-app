package com.example.todo_app


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import com.example.ToDo_app.R
import java.util.Vector

class ListToDoAdapter(context: Context, items: Vector<ToDo>) : BaseAdapter() {
    private val context: Context
    private val items: Vector<ToDo>

    override fun getCount(): Int {
        return items.size //returns total of items in the list
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):
            View? {
        var convertView: View? = convertView
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                .inflate(R.layout.to_do_row, parent, false)
        }
        val currentItem = getItem(position) as ToDo
        val tx_title = convertView
            ?.findViewById(R.id.tx_title) as TextView
        val tx_desc = convertView
            ?.findViewById(R.id.tx_desc) as TextView
        val checkbox_done = convertView?.findViewById(R.id.checkbox_done) as CheckBox
        tx_title.text = currentItem.title
        tx_desc.text = currentItem.desc
        checkbox_done.isChecked = currentItem.done // Set the checkbox based on the 'done' property
        checkbox_done.setOnCheckedChangeListener { _, isChecked ->
            currentItem.done = !currentItem.done
            currentItem.done = !currentItem.done

        }

        var bt_details = convertView?.findViewById<Button>(R.id.bt_details) as Button
        bt_details.setOnClickListener {
            val intent = Intent(context, EditToDo::class.java)
            intent.putExtra("todo_index", position)
            context.startActivity(intent)

        }

        return convertView
    }

    init {
        this.context = context
        this.items = items
    }
}