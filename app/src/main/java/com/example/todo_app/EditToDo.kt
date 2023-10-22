package com.example.todo_app

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ToDo_app.R

class EditToDo : AppCompatActivity() {
    var ToDo: ToDo? = null
    var position_id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_to_do)
        val extras = intent.extras
        if (extras != null) {
            position_id = extras.getInt("todo_index")
            ToDo = MainActivity.data.get(position_id) as ToDo
            var tx_title = findViewById<EditText>(R.id.tx_title)
            var tx_desc = findViewById<EditText>(R.id.tx_desc)

            tx_title.setText(ToDo!!.title)
            tx_desc.setText(ToDo!!.desc)

        }
        var bt_save = findViewById<Button>(R.id.bt_save)
        bt_save.setOnClickListener {
            ToDo?.title = findViewById<EditText>(R.id.tx_title).text.toString()
            ToDo?.desc = findViewById<EditText>(R.id.tx_desc).text.toString()


            val intent = Intent()
            this.setResult(RESULT_OK, intent)
            this.finish()
        }
        val bt_delete = findViewById<Button>(R.id.bt_delete)
        bt_delete.setOnClickListener {
            MainActivity.data.removeElementAt(position_id)
            val intent = Intent()
            Log.i("Hye there ", MainActivity.data.toString())
            this.setResult(RESULT_OK, intent)
            this.finish()
        }

    }
}