package com.rommelrico.notesandroid

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    internal var notes = ArrayList<String>()
    internal var arrayAdapter: ArrayAdapter<*>? = null
    internal var sharedPreferences: SharedPreferences? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
