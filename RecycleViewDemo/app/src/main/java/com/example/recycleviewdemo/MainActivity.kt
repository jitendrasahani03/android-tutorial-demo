package com.example.recycleviewdemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var editTextFirst: EditText
    private lateinit var addButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editTextFirst = findViewById(R.id.edtFirstName)
        addButton = findViewById(R.id.addButton)

        val personDataList = arrayListOf<PersonData>()
        personDataList.add(PersonData("Jitendra"))
        personDataList.add(PersonData("Anil"))
        val myAdapter = MyAdapter(personDataList)

        recyclerView = findViewById(R.id.recycleview)
        recyclerView.adapter = myAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        addButton.setOnClickListener {
            val newPerson = editTextFirst.text.toString()
            if (newPerson.isNotEmpty() || newPerson.isNotBlank()){
                personDataList.add(PersonData(newPerson))
                myAdapter.updateList(personDataList)
            }
            editTextFirst.text.clear()
        }

    }
}