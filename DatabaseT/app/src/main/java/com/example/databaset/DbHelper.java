package com.example.databaset;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "vandalism.db";

    public static final String TABLE_NAME = "vandalism";
    public static final String COL_TYPE = "type";
    public static final String COL_ADDRESS = "address";
    public static final String COL_CODE_OF_THE_PHOTO = "photo";
    public static final String COL_OBJECT_OF_VANDALISM = "target";
    public static final String COL_NUMBER_OF_VOTES = "votes";
    public static final int VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
         String createQuery = String.format(
                "CREATE TABLE %s ("+
                "_id integer primary key autoincrement, "+
                "%s text NOT NULL, "+
                "%s integer NOT NULL UNIQUE, "+
                "%s text NOT NULL, "+
                "%s text NOT NULL, "+
                "%s integer NOT NULL DEFAULT 0);",
                TABLE_NAME,
                COL_ADDRESS,
                COL_CODE_OF_THE_PHOTO,
                COL_OBJECT_OF_VANDALISM,
                COL_TYPE,
                COL_NUMBER_OF_VOTES
         );
         sqLiteDatabase.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String upgradeQuery = String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        sqLiteDatabase.execSQL(upgradeQuery);
        onCreate(sqLiteDatabase);

    }
}
