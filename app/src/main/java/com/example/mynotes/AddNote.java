package com.example.mynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.mynotes.adapter.NoteAdapter;
import com.example.mynotes.model.Notes;
import com.example.mynotes.sqlite.DbHelper;

public class AddNote extends AppCompatActivity {

    EditText edtTitle, edtNote;
    Button btnSave;

    DbHelper helper;

    String id, title, note;
    int action = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        initView();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNote();
            }
        });
    }

    void getDataFoUpdate() {
        id = getIntent().getStringExtra(NoteAdapter.ID_KEY);
        title = getIntent().getStringExtra(NoteAdapter.TITLE_KEY);
        note = getIntent().getStringExtra(NoteAdapter.NOTE_KEY);
        action = getIntent().getIntExtra(NoteAdapter.ACTION_KEY, 0);
    }

    private void initView() {

        helper = new DbHelper(this);

        edtTitle = findViewById(R.id.edt_title);
        edtNote = findViewById(R.id.edt_note);
        btnSave = findViewById(R.id.btn_save);
    }

    void addNote() {
        String title = edtTitle.getText().toString();
        String note = edtNote.getText().toString();

        helper.addNote(title, note);
    }

}
