package com.rommelrico.notesandroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class NoteEditorActivity : AppCompatActivity() {

    internal var noteId: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_note_editor)
    }
}
