package com.example.notes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLite extends SQLiteOpenHelper {

    private Context context;
    private static final String Database_ismi = "Notes.db";
    private static final int version=1;
    private static final String TABLO_ISMI = "NOTES";
    private static final String SATIR_ID = "_id";
    private static final String HEADER = "header";
    private static final String NOTES = "notes";



    public SQLite(@Nullable Context context) {
        super(context, Database_ismi, null, version);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String yaratNotlar = "CREATE TABLE " + TABLO_ISMI + " (" + SATIR_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +  HEADER + " TEXT , " + NOTES + " TEXT);";
        sqLiteDatabase.execSQL(yaratNotlar);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLO_ISMI);
        onCreate(sqLiteDatabase);
    }

    void addNote(String header, String notes){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(HEADER, header);
        cv.put(NOTES, notes);
        db.insert(TABLO_ISMI,null,cv);
    }

    Cursor getNotes(){
        String query= "SELECT * FROM " + TABLO_ISMI;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor c=null;
        if (db != null){
            c=db.rawQuery(query,null);
        }

        return c;
    }



    void removeNote(String id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLO_ISMI, "_id=?",new String[]{id});
    }


    /*
    void duzenle(int idb,String yeninot){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(NOTLAR ,yeninot);

        db.update(TABLO_ISMI,cv,"_id=?",new String[]{idb+""});
    }*/

}