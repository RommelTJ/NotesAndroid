package com.rommelrico.notesandroid

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.ArrayList
import java.util.HashSet

class MainActivity : AppCompatActivity() {

    internal var notes = ArrayList<String>()
    internal var arrayAdapter: ArrayAdapter<*>? = null
    internal var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = applicationContext.getSharedPreferences("com.rommelrico.notesandroid", Context.MODE_PRIVATE)
        val listView = findViewById<ListView>(R.id.listView)

        val set = sharedPreferences?.getStringSet("notes", null) as HashSet<String>

        if (set == null) {
            notes.add("Example Note")
        } else {
            notes = ArrayList(set)
        }
    }
}
