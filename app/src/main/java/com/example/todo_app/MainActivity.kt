package com.example.todo_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ToDo_app.R
import java.util.Vector

class MainActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        Log.i("resuming", data.toString())
        drawToDo()
    }

    private fun printToDos() {
        println(">>>>>Printing ToDos" + data.size)
        for (item in data) {
            println(">>>>>" + item.title)
        }
    }

    companion object {
        var data = Vector<ToDo>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var bt_new = findViewById<Button>(R.id.bt_new)
        bt_new.setOnClickListener {
            val intent = Intent(this, NewToDo::class.java)
            intent.putExtra("some_variable", "HelloWorld !")
            intent.putExtra("another_variable", 12)
            launchActivityNewToDo.launch(intent)
        }

        var lv_ToDos = findViewById<ListView>(R.id.lv_ToDo)
        lv_ToDos.setOnItemClickListener { parent, view, position, id ->
            Log.i("id of element is :", id.toString())
            Log.i("position of the element is:", position.toString())
            val intent = Intent(this, EditToDo::class.java)
            intent.putExtra("todo_index", position)
            launchActivityNewToDo.launch(intent)
        }
    }

    var launchActivityNewToDo = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        println("Get data back<<<<<<Result Code : " + result.resultCode)
        if (result.resultCode == Activity.RESULT_OK) {
            println("Get data back<<<<<<")
            printToDos()
            drawToDo()
        }
    }

    private fun drawToDo() {
        var lv_ToDos = findViewById<ListView>(R.id.lv_ToDo)
        lv_ToDos.adapter = ListToDoAdapter(this, data)
        lv_ToDos.refreshDrawableState()
    }

}