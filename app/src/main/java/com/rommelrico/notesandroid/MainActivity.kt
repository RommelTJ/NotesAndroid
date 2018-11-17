package com.rommelrico.notesandroid

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import java.util.ArrayList
import java.util.HashSet

class MainActivity : AppCompatActivity() {

    companion object {
        internal var notes = ArrayList<String>()
        internal var arrayAdapter: ArrayAdapter<*>? = null
        internal var sharedPreferences: SharedPreferences? = null
    }

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

        arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, notes)
        listView.adapter = arrayAdapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, i, _ ->
            val intent = Intent(applicationContext, NoteEditorActivity::class.java)
            intent.putExtra("noteId", i)
            startActivity(intent)
        }

        listView.onItemLongClickListener = AdapterView.OnItemLongClickListener { _, _, i, _ ->
            val itemToDelete = i

            AlertDialog.Builder(this@MainActivity)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Are you sure?")
                    .setMessage("Do you want to delete this note?")
                    .setPositiveButton("Yes") { dialogInterface, i ->
                        notes.removeAt(itemToDelete)
                        arrayAdapter?.notifyDataSetChanged()

                        val set = HashSet<String>(MainActivity.notes)
                        sharedPreferences?.edit()?.putStringSet("notes", set)?.apply()
                    }
                    .setNegativeButton("No", null)
                    .show()
            true
        }
    } // end onCreate

    // Menu Overrides

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val menuInflater = menuInflater
        menuInflater.inflate(R.menu.add_note_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        super.onOptionsItemSelected(item)

        if (item?.itemId == R.id.add_note) {
            val intent = Intent(applicationContext, NoteEditorActivity::class.java)
            startActivity(intent)

            return true
        }

        return false
    }

}
