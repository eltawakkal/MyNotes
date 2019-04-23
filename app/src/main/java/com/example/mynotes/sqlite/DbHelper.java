package com.example.mynotes.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.mynotes.model.Notes;

import java.util.ArrayList;
import java.util.List;

public class DbHelper {

    SqliteHelper sqliteHelper;
    SQLiteDatabase database;
    Cursor cursor;

    Context context;

    public DbHelper(Context context) {
        this.context = context;

        sqliteHelper = new SqliteHelper(context);
    }

    public List<Notes> getAllData() {
        database = sqliteHelper.getReadableDatabase();

        String sql = "SELECT * FROM " + SqliteHelper.TB_NAME;
        cursor = database.rawQuery(sql, null);

        if (cursor.getCount() == 0) {
            Toast.makeText(context, "Database Kosong!", Toast.LENGTH_SHORT).show();
        } else {

            List<Notes> listNotes = new ArrayList<>();

            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                String id = cursor.getString(0);
                String title = cursor.getString(1);
                String note = cursor.getString(2);
                Notes notes = new Notes(id, title, note);
                listNotes.add(notes);

                cursor.moveToNext();
            }

            Toast.makeText(context, "Jumlah Database: " + cursor.getCount(), Toast.LENGTH_SHORT).show();
            return listNotes;
        }

        return null;
    }

    public void addNote(String title, String note) {

        database = sqliteHelper.getWritableDatabase();

        String sql = "INSERT INTO " + SqliteHelper.TB_NAME + " (title, note) VALUES ('" + title +"', '" + note + "')";
        database.execSQL(sql);
    }

    public void updateNote(String id, String title, String note) {
        database = sqliteHelper.getWritableDatabase();
        String sql = "UPDATE note SET title ='" + title +"', note = '" + note + "' WHERE id ='" + id + "'";
        database.execSQL(sql);
    }

    public void deleteUser(String id) {
        database = sqliteHelper.getWritableDatabase();
        String sql = "DELETE FROM note WHERE id = '"+ id +"'";
        database.execSQL(sql);
    }

}
