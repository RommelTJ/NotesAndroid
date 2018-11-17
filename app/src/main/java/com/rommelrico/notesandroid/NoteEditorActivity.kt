package com.rommelrico.notesandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class NoteEditorActivity : AppCompatActivity() {

    internal var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)

        val editText = findViewById<EditText>(R.id.editText)

        val intent = intent
        noteId = intent.getIntExtra("noteId", -1)
        
    }
}
