package com.joshuafoster.budgetmaster;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySqlLiteHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "budgetmaster.sqlite";
    private static final int DB_VERSION = 1;
    public static final String TRANSACTION_TABLE = "Transactions",
            CATEGORY_TABLE = "Categories",
            BUDGET_TABLE = "Budgets",
            VENDOR_TABLE = "Vendors";
    public static final String TRANS_ID = "trans_id",
            TRANS_DATE = "trans_date",
            TRANS_AMOUNT = "trans_amount",
            TRANS_DESCRIPTION = "trans_description";
    public static final String VENDOR_ID = "vendor_id",
            VENDOR_NAME = "vendor_name";
    public static final String CAT_ID = "cat_id",
            CAT_NAME = "cat_name",
            CAT_DESCRIPTION = "cat_description";
    public static final String BUDGET_ID = "budget_id",
            BUDGET_NAME = "budget_name",
            BUDGET_START = "budget_start",
            BUDGET_END = "budget_end";


    public MySqlLiteHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create transactions table
        String sql = "CREATE TABLE " + TRANSACTION_TABLE + " (" +
                TRANS_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                TRANS_DATE + " TEXT NOT NULL , " +
                TRANS_AMOUNT + " REAL NOT NULL , " +
                TRANS_DESCRIPTION + " TEXT, " +
                VENDOR_ID + " INTEGER NOT NULL , " +
                CAT_ID + " INTEGER NOT NULL )";
        db.execSQL(sql);
        Log.i("Database", sql);

        //Create vendor table
        sql = "CREATE TABLE " + VENDOR_TABLE + " (" +
                VENDOR_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                VENDOR_NAME + " TEXT NOT NULL )";
        db.execSQL(sql);
        Log.i("Database", sql);

        //Create vendor table
        sql = "CREATE TABLE " + CATEGORY_TABLE + " (" +
                CAT_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                CAT_NAME + " TEXT NOT NULL, " +
                CAT_DESCRIPTION + " TEXT NOT NULL )";
        db.execSQL(sql);
        Log.i("Database", sql);

        sql = "CREATE TABLE " + BUDGET_TABLE + " (" +
                BUDGET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                BUDGET_NAME + " TEXT NOT NULL, " +
                BUDGET_START + " INTEGER NOT NULL, " +
                BUDGET_END + " INTEGER NOT NULL )";
        db.execSQL(sql);
        Log.i("Database", sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
