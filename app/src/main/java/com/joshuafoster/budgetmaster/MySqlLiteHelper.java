package com.joshuafoster.budgetmaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "budgetmaster.sqlite";
    private static final int DB_VERSION = 1;
    public static final String TRANSACTION_TABLE = "Transactions";
    public static final String ID = "id";
    public static final String DATE = "date";
    public static final String AMOUNT = "amount";
    public static final String VENDOR = "vendor";
    public static final String CATEGORY = "category";

    public enum TransactionColumns {
        id, date, amount, vendor, category
    }

    public MySqlLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TRANSACTION_TABLE + " (" +
                ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                DATE + " TEXT NOT NULL , " +
                VENDOR + " TEXT NOT NULL , " +
                AMOUNT + " REAL NOT NULL , " +
                CATEGORY + " TEXT NOT NULL )";
        db.execSQL(sql);
        }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
