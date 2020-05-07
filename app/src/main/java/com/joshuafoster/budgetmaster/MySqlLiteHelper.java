package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

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
            VENDOR_NAME = "vendor_name",
            VENDOR_TYPE = "vendor_type";
    public static final String CAT_ID = "cat_id",
            CAT_NAME = "cat_name",
            CAT_DESCRIPTION = "cat_description",
            CAT_TYPE = "cat_type";
    public static final String BUDGET_ID = "budget_id",
            BUDGET_NAME = "budget_name",
            BUDGET_START = "budget_start",
            BUDGET_END = "budget_end";
    public static final String EXPENSES_TYPE = "expenses",
            INCOME_TYPE = "income";
    public static final String[] CATEGORIES_INCOME = {"Salary", "Rentals", "Bonus", "Child Support", "Social Security Benefits", "Cash Gift", "Interest and Dividends"};
    public static final String[] CATEGORIES_EXPENSES = {"Groceries", "Restaurants", "Gas", "Utilities", "Auto Insurance", "Mortgage"};
    public static final String[] VENDORS_INCOME = {"Employer", "Social Security", "Cabin Rental", "Bank"};
    public static final String[] VENDORS_EXPENSES = {"Walmart", "Applebees", "Telecom", "Mortgage Services LLC", "Gas company", "Geico"};
    public static final DateFormat DATE_FORMAT_DB = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
    public static final DateFormat DATE_FORMAT_TEXT = new SimpleDateFormat("MM-dd-yy", Locale.ENGLISH);



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

        //Create vendor table
        sql = "CREATE TABLE " + VENDOR_TABLE + " (" +
                VENDOR_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                VENDOR_NAME + " TEXT NOT NULL , " +
                VENDOR_TYPE + " TEXT NOT NULL)";
        db.execSQL(sql);

        //Create vendor table
        sql = "CREATE TABLE " + CATEGORY_TABLE + " (" +
                CAT_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT  NOT NULL  UNIQUE , " +
                CAT_NAME + " TEXT NOT NULL, " +
                CAT_DESCRIPTION + " TEXT NOT NULL , " +
                CAT_TYPE + " TEXT NOT NULL)";
        db.execSQL(sql);

        sql = "CREATE TABLE " + BUDGET_TABLE + " (" +
                BUDGET_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE , " +
                BUDGET_NAME + " TEXT NOT NULL, " +
                BUDGET_START + " INTEGER NOT NULL, " +
                BUDGET_END + " INTEGER NOT NULL )";
        db.execSQL(sql);

        Random random = new Random();

        for (int i = 0; i < 10; i++) { //add 10 expenses
            int randomCategory = random.nextInt(CATEGORIES_EXPENSES.length) + 1;
            int randomVendor = random.nextInt(VENDORS_EXPENSES.length) + 1;
            ContentValues values = new ContentValues();
            values.put(MySqlLiteHelper.TRANS_DATE, DATE_FORMAT_DB.format(Calendar.getInstance().getTime()));
            values.put(MySqlLiteHelper.TRANS_AMOUNT, String.format("%.2f", random.nextDouble()*-100));
            values.put(MySqlLiteHelper.VENDOR_ID, randomVendor);
            values.put(MySqlLiteHelper.CAT_ID, randomCategory);

            // Inserting Row
            db.insert(MySqlLiteHelper.TRANSACTION_TABLE, null, values);
            //2nd argument is String containing nullColumnHack
        }

        for (int i = 0; i < VENDORS_EXPENSES.length; i++) { //add vendors and categories for expenses
            ContentValues values = new ContentValues();
            values.put(MySqlLiteHelper.VENDOR_NAME, VENDORS_EXPENSES[i]);
            values.put(MySqlLiteHelper.VENDOR_TYPE, EXPENSES_TYPE);
            db.insert(MySqlLiteHelper.VENDOR_TABLE, null, values);
        }
        for (int i = 0; i < CATEGORIES_EXPENSES.length; i++) {
            ContentValues values = new ContentValues();
            values.put(MySqlLiteHelper.CAT_NAME, CATEGORIES_EXPENSES[i]);
            values.put(MySqlLiteHelper.CAT_DESCRIPTION, "Test data");
            values.put(MySqlLiteHelper.CAT_TYPE, EXPENSES_TYPE);
            db.insert(MySqlLiteHelper.CATEGORY_TABLE, null, values);
        }

        for (int i = 0; i < 5; i++) { //add 5 income transactions
            int randomCategory = random.nextInt(CATEGORIES_INCOME.length) + CATEGORIES_EXPENSES.length + 1;
            int randomVendor = random.nextInt(VENDORS_INCOME.length) + VENDORS_EXPENSES.length + 1;

            ContentValues values = new ContentValues();
            values.put(MySqlLiteHelper.TRANS_DATE, DATE_FORMAT_DB.format(Calendar.getInstance().getTime()));
            values.put(MySqlLiteHelper.TRANS_AMOUNT, String.format("%.2f", random.nextDouble()*1000));
            values.put(MySqlLiteHelper.VENDOR_ID, randomVendor);
            values.put(MySqlLiteHelper.CAT_ID, randomCategory);

            // Inserting Row
            db.insert(MySqlLiteHelper.TRANSACTION_TABLE, null, values);
            //2nd argument is String containing nullColumnHack
        }

        for (int i = 0; i < VENDORS_INCOME.length; i++) { //add vendors and categories for expenses
            ContentValues values = new ContentValues();
            values.put(MySqlLiteHelper.VENDOR_NAME, VENDORS_INCOME[i]);
            values.put(MySqlLiteHelper.VENDOR_TYPE, INCOME_TYPE);
            db.insert(MySqlLiteHelper.VENDOR_TABLE, null, values);
        }
        for (int i = 0; i < CATEGORIES_INCOME.length; i++){
            ContentValues values = new ContentValues();
            values.put(MySqlLiteHelper.CAT_NAME, CATEGORIES_INCOME[i]);
            values.put(MySqlLiteHelper.CAT_DESCRIPTION, "Test data");
            values.put(MySqlLiteHelper.CAT_TYPE, INCOME_TYPE);
            db.insert(MySqlLiteHelper.CATEGORY_TABLE, null, values);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
