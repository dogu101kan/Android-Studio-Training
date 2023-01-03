package com.example.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ActiviyNotes extends AppCompatActivity {

    Button back;
    ListView lv;
    ArrayList<String> headers = new ArrayList<>();
    ArrayList<String> notes = new ArrayList<>();
    ArrayList<Integer> id = new ArrayList<>();
    ArrayAdapter<String> adapter;
    SQLite db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activiy_notes);

        lv = findViewById(R.id.lv);
        back = findViewById(R.id.mainActivity);

        db = new SQLite(this);

        Cursor c = db.getNotes();

        if(c.moveToFirst()){

            do{
                id.add(c.getInt(0));
                headers.add(c.getString(1));
                notes.add(c.getString(2));
            }while(c.moveToNext());
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, headers);
        lv.setAdapter(adapter);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainActivity = new Intent(ActiviyNotes.this, MainActivity.class);
                startActivity(mainActivity);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent getNote = new Intent(ActiviyNotes.this, seeNote.class);
                getNote.putExtra("note", (notes.get(i)));
                getNote.putExtra("header", (headers.get(i)));

                startActivity(getNote);

            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                notes.remove(i);
                headers.remove(i);
                db.removeNote(id.get(i) + "");
                id.remove(i);
                adapter.notifyDataSetChanged();
                return false;
            }
        });


    }
}