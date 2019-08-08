package com.alvaro.opos.data.source.local.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alvaro.opos.data.source.local.db.DbExerciseLocalDatasource.ExerciseTable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "exerciseDB";
    private static final int DATABASE_VERSION = 4;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            dropTables(db);
            onCreate(db);
        }
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL(ExerciseTable.CREATE_TABLE);
    }

    private void dropTables(SQLiteDatabase db) {
        db.execSQL(ExerciseTable.DROP_TABLE);
    }
}
