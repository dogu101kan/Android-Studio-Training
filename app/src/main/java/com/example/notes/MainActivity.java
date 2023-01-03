package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button save, notes;
    EditText inHeader, inNotes;
    SQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        save = findViewById(R.id.save);
        notes = findViewById(R.id.notes);
        inHeader = findViewById(R.id.headerInput);
        inNotes = findViewById(R.id.noteInput);

        db = new SQLite(this);

        save.setOnClickListener(this);
        notes.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:

                try{
                    db.addNote(inHeader.getText().toString().trim(), inNotes.getText().toString().trim());
                    inHeader.setText("");
                    inNotes.setText("");
                }finally {
                    db.close();
                }

                break;

            case R.id.notes:

                inHeader.setText("");
                inNotes.setText("");

                Intent notesButton = new Intent(MainActivity.this, ActiviyNotes.class);
                startActivity(notesButton);
                break;
        }
    }
}