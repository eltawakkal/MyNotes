package com.example.mynotes.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqliteHelper extends SQLiteOpenHelper {

    Context context;

    private static final String DB_NAME = "notes";
    private static final int DB_VERSION = 1;

    //tables
    public static final String TB_NAME = "note";

    //fields
    private static final String FIELD_ID = "id";
    private static final String FIELD_TITLE = "title";
    private static final String FIELD_NOTES = "note";

    //create table
    private static final String CREATE_TABLE =
                    "CREATE TABLE " + TB_NAME + " (" +
                    FIELD_ID +  " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    FIELD_TITLE + " VARCHAR(16)," +
                    FIELD_NOTES + " TEXT)";

    public SqliteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
