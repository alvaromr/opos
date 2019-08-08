package com.alvaro.opos.data.source.local.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.alvaro.opos.data.entity.ExerciseEntity;
import com.alvaro.opos.data.source.local.LocalDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DbExerciseLocalDatasource implements LocalDataSource<ExerciseEntity> {

    private SQLiteOpenHelper sqLiteOpenHelper;

    public DbExerciseLocalDatasource(SQLiteOpenHelper sqLiteOpenHelper) {
        this.sqLiteOpenHelper = sqLiteOpenHelper;
    }

    class ExerciseTable {
        static final String TABLE_NAME = "exercise";

        static final String COLUMN_ID = "id";
        static final String COLUMN_QUESTION = "question";
        static final String COLUMN_POSSIBLE_ANSWERS = "possible_answers";
        static final String COLUMN_CORRECT_ANSWER = "correct_answer";

        static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
        static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                "(" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_QUESTION + " TEXT," +
                COLUMN_POSSIBLE_ANSWERS + " TEXT," +
                COLUMN_CORRECT_ANSWER + " INTEGER" +
                ")";
    }

    @Override
    public List<ExerciseEntity> getAll() {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + ExerciseTable.TABLE_NAME;

        List<ExerciseEntity> exerciseEntities = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            do {
                exerciseEntities.add(from(cursor));
            } while (cursor.moveToNext());
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }
        return exerciseEntities;
    }

    @Override
    public ExerciseEntity get(Long id) {
        SQLiteDatabase db = sqLiteOpenHelper.getReadableDatabase();
        String sql = "SELECT * FROM " + ExerciseTable.TABLE_NAME + " WHERE " + ExerciseTable.COLUMN_ID + " = " + id;
        ExerciseEntity exerciseEntity = null;

        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            exerciseEntity = from(cursor);
        }

        if (!cursor.isClosed()) {
            cursor.close();
        }

        return exerciseEntity;
    }

    @Override
    public ExerciseEntity save(ExerciseEntity exerciseEntity) {
        if (exerciseEntity.id == -1) {
            exerciseEntity.id = (long)new Random().nextInt(); //TODO
        }
        ExerciseEntity entity = get(exerciseEntity.id);
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        if (entity == null) {
            db.insert(ExerciseTable.TABLE_NAME, null, toContentValues(exerciseEntity));
            entity = get(exerciseEntity.id);
        } else {
            db.update(ExerciseTable.TABLE_NAME,
                    toContentValues(exerciseEntity),
                    ExerciseTable.COLUMN_ID + " = ?",
                    new String[]{ exerciseEntity.id.toString() });
        }
        return entity;
    }

    @Override
    public void deleteAll() {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.delete(ExerciseTable.TABLE_NAME, null, null);
    }

    @Override
    public void delete(ExerciseEntity exerciseEntity) {
        SQLiteDatabase db = sqLiteOpenHelper.getWritableDatabase();
        db.delete(ExerciseTable.TABLE_NAME, ExerciseTable.COLUMN_ID + " = ?", new String[]{ exerciseEntity.id.toString() });
    }

    private ExerciseEntity from(Cursor cursor) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.id = cursor.getLong(cursor.getColumnIndex(ExerciseTable.COLUMN_ID));
        entity.question = cursor.getString(cursor.getColumnIndex(ExerciseTable.COLUMN_QUESTION));
        entity.possibleAnswers = cursor.getString(cursor.getColumnIndex(ExerciseTable.COLUMN_POSSIBLE_ANSWERS));
        entity.correctAnswer = cursor.getInt(cursor.getColumnIndex(ExerciseTable.COLUMN_CORRECT_ANSWER));
        return entity;
    }

    private ContentValues toContentValues(ExerciseEntity exerciseEntity) {
        ContentValues values = new ContentValues();
        values.put(ExerciseTable.COLUMN_ID, exerciseEntity.id);
        values.put(ExerciseTable.COLUMN_QUESTION, exerciseEntity.question);
        values.put(ExerciseTable.COLUMN_POSSIBLE_ANSWERS, exerciseEntity.possibleAnswers);
        values.put(ExerciseTable.COLUMN_CORRECT_ANSWER, exerciseEntity.correctAnswer);
        return values;
    }
}
