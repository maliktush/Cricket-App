package com.tusharmalik.playersearch;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import static com.tusharmalik.playersearch.Const.CMD_CREATE_TABLE_INE;
import static com.tusharmalik.playersearch.Const.COMMA;
import static com.tusharmalik.playersearch.Const.LBR;
import static com.tusharmalik.playersearch.Const.RBR;
import static com.tusharmalik.playersearch.Const.SEMI;
import static com.tusharmalik.playersearch.Const.TYPE_INT;
import static com.tusharmalik.playersearch.Const.TYPE_PK_AI;
import static com.tusharmalik.playersearch.Const.TYPE_TEXT;

/**
 * Created by tushm on 13-01-2019.
 */

public class PlayerTable {
    private PlayerTable() {
    }

    public static final String TABLE_NAME = "Player";

    public interface Columns {
        String ID = "id";
        String NAME = "name";
        String COUNTRY = "country";
        String TYPE = "type";

    }

    public static final String CMD_CREATE_TABLE =
            CMD_CREATE_TABLE_INE + TABLE_NAME
                    + LBR
                    + Columns.ID + TYPE_INT + TYPE_PK_AI + COMMA
                    + Columns.NAME + TYPE_TEXT + COMMA
                    + Columns.COUNTRY + TYPE_TEXT + COMMA
                    + Columns.TYPE + TYPE_TEXT
                    + RBR + SEMI;

    public static long insertBuyerItem(Player seller, SQLiteDatabase db) {
        ContentValues newBuyerItem = new ContentValues();

        newBuyerItem.put(Columns.NAME, seller.getName());
        newBuyerItem.put(Columns.COUNTRY, seller.getCountry());
        newBuyerItem.put(Columns.TYPE, seller.getType());
        return db.insert(TABLE_NAME, null, newBuyerItem);
    }
    public static ArrayList<Player> getAllPlayers (SQLiteDatabase db) {
        Cursor c = db.query(
                TABLE_NAME,
                new String[]{Columns.ID, Columns.NAME, Columns.COUNTRY,Columns.TYPE},
                null,
                null,
                null,
                null,
                null
        );
        ArrayList<Player> Records = new ArrayList<>();
        c.moveToFirst();
        int idIndex = c.getColumnIndex(Columns.ID);
        int nameIndex = c.getColumnIndex(Columns.NAME);
        int countryIndex = c.getColumnIndex(Columns.COUNTRY);
        int typeIndex = c.getColumnIndex(Columns.TYPE);


        while (!c.isAfterLast()) {
            Records.add(new Player(
                    c.getInt(idIndex),
                    c.getString(nameIndex),
                    c.getString(countryIndex),
                    c.getString(typeIndex)

            ));

            c.moveToNext();
        }
        return Records;
    }
}