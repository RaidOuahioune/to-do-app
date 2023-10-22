package com.example.todo_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.ToDo_app.R


class NewToDo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_do_do_activity)
        var bt_cancel = findViewById<Button>(R.id.bt_cancel)
        bt_cancel.setOnClickListener {
            this.finish()
        }
        var bt_add = findViewById<Button>(R.id.bt_add)
        bt_add.setOnClickListener {
            var nameVal = findViewById<EditText>(R.id.tx_title).text.toString()
            var descVal = findViewById<EditText>(R.id.tx_desc).text.toString()
            MainActivity.data.add(
                ToDo(
                    MainActivity.data.maxOfOrNull { it.id }?.plus(1) ?: (1
                            + 1), nameVal, descVal
                )
            )
            val intent = Intent()
            intent.putExtra("some_var_back", "AnyValueYouWant")
            this.setResult(RESULT_OK, intent)
            this.finish()
        }
    }
}