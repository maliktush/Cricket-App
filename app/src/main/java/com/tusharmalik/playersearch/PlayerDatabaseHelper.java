package com.tusharmalik.playersearch;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static com.tusharmalik.playersearch.PlayerTable.TABLE_NAME;

/**
 * Created by tushm on 13-01-2019.
 */

public class PlayerDatabaseHelper extends SQLiteOpenHelper{
    public static final String DB_NAME = "Player.db";
    public static final int DB_VER = 1;
    public PlayerDatabaseHelper(Context context) {
        super(context, DB_NAME,null,DB_VER);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(PlayerTable.CMD_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }public static Cursor getInfoSearch(String word, SQLiteDatabase db){

//        SQLiteDatabase db = this.getWritableDatabase();
        String searchitem="%"+word+"%";
        String query = "SELECT * FROM "+ TABLE_NAME + " WHERE name LIKE '" + searchitem + "'" ;
        Cursor data = db.rawQuery(query, null);

        return data;

    }
}
