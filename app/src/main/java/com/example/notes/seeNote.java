package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class seeNote extends AppCompatActivity {

    TextView header, note;
    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_note);

        header = findViewById(R.id.TVheader);
        note = findViewById(R.id.TVnote);
        back = findViewById(R.id.activiyNotes);

        header.setText(getIntent().getStringExtra("header"));
        note.setText(getIntent().getStringExtra("note"));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent activiyNotes = new Intent(seeNote.this, ActiviyNotes.class);
                startActivity(activiyNotes);
            }
        });
    }
}