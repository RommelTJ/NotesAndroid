package com.rommelrico.notesandroid

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class NoteEditorActivity : AppCompatActivity() {

    internal var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        val editText = findViewById<EditText>(R.id.editText)

        val intent = intent
        noteId = intent.getIntExtra("noteId", -1)

        if (noteId != -1) {
            editText.setText(MainActivity.notes[noteId])
        } else {
            MainActivity.notes.add("")
            noteId = MainActivity.notes.size - 1
        }

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) { }

            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                MainActivity.notes[noteId] = charSequence.toString()
                MainActivity.arrayAdapter?.notifyDataSetChanged()

                val sharedPreferences = applicationContext.getSharedPreferences("com.example.zappycode.notes", Context.MODE_PRIVATE)
                val set = HashSet(MainActivity.notes)
                sharedPreferences.edit().putStringSet("notes", set).apply()
            }

            override fun afterTextChanged(editable: Editable) { }
        })
    }
}
