package com.example.styledemo

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var tinyDB: TinyDB

    val ITEM_SAVE_POSITION = "ITEM_SAVE_POSITION"

    var savePosition = 0

    var country = arrayOf("India", "USA", "China", "Japan", "Other")

    lateinit var adapter: ArrayAdapter<String>

    override fun onResume() {
        super.onResume()
        spinnerMain.setSelection(tinyDB.getInt(ITEM_SAVE_POSITION))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tinyDB = TinyDB(this)

        savePosition = tinyDB.getInt(ITEM_SAVE_POSITION)

        adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, country)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinnerMain.adapter = adapter

        val spinnerListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                tinyDB.putInt(ITEM_SAVE_POSITION, position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                spinnerMain.setSelection(tinyDB.getInt(ITEM_SAVE_POSITION))
            }

        }

        spinnerMain.onItemSelectedListener = spinnerListener

    }
}