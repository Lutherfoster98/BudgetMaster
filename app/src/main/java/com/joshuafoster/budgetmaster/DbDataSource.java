package com.joshuafoster.budgetmaster;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.joshuafoster.budgetmaster.MySqlLiteHelper;

import java.lang.reflect.Array;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DbDataSource {

    private SQLiteDatabase database;
    private MySqlLiteHelper databaseHelper;

    public DbDataSource(Context context) {
        databaseHelper = new MySqlLiteHelper(context);
    }

    public void openForReading() {
        database = databaseHelper.getReadableDatabase();
    }

    public void openForWriting() {
        database = databaseHelper.getWritableDatabase();
    }

    public void close() {
        database.close();
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);

        String query = "SELECT " +
                MySqlLiteHelper.TRANS_ID + ", " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.TRANS_DATE + ", " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.TRANS_AMOUNT + ", " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.TRANS_DESCRIPTION + ", " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.VENDOR_ID + ", " +
                MySqlLiteHelper.VENDOR_TABLE + "." + MySqlLiteHelper.VENDOR_NAME + ", " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.CAT_ID + ", " +
                MySqlLiteHelper.CATEGORY_TABLE + "." + MySqlLiteHelper.CAT_NAME + " FROM " +
                MySqlLiteHelper.TRANSACTION_TABLE + " INNER JOIN " +
                MySqlLiteHelper.VENDOR_TABLE + " ON " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.VENDOR_ID + " = " +
                MySqlLiteHelper.VENDOR_TABLE + "." + MySqlLiteHelper.VENDOR_ID + " INNER JOIN " +
                MySqlLiteHelper.CATEGORY_TABLE + " ON " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.CAT_ID + " = " +
                MySqlLiteHelper.CATEGORY_TABLE + "." + MySqlLiteHelper.CAT_ID + ";";


        Cursor cursor = database.rawQuery(query, null);
        Log.i("Tracking", "Rows returned: " + cursor.getCount());

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setId(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.TRANS_ID)));
                try {
                    transaction.setDate(dateFormat.parse(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.TRANS_DATE))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                transaction.setVendor_id(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.VENDOR_ID)));
                // Adding contact to list
                transactions.add(transaction);
            } while (cursor.moveToNext());
        }

        return transactions;
    }

    public Cursor getCursor(String query){
        Cursor cursor = database.rawQuery(query, null);
        return cursor;
    }

    void addTransaction(Transaction transaction) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);

        ContentValues values = new ContentValues();
        values.put(MySqlLiteHelper.TRANS_DATE, dateFormat.format(transaction.getDate())); // Contact Name
        values.put(MySqlLiteHelper.TRANS_AMOUNT, String.format("%.2f", transaction.getAmount()));
        if (!transaction.getDescription().isEmpty())
            values.put(MySqlLiteHelper.TRANS_DESCRIPTION, transaction.getDescription());
        values.put(MySqlLiteHelper.VENDOR_ID, transaction.getVendor_id()); // Contact Phone
        values.put(MySqlLiteHelper.CAT_ID, transaction.getCat_id());

        // Inserting Row
        database.insert(MySqlLiteHelper.TRANSACTION_TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        Log.i("Database", "Added record: " + transaction.toString());
    }

    void addCategory(String name, String description){
        ContentValues values = new ContentValues();
        values.put(MySqlLiteHelper.CAT_NAME, name);
        values.put(MySqlLiteHelper.CAT_DESCRIPTION, description);
        database.insert(MySqlLiteHelper.CATEGORY_TABLE, null, values);
    }

    void addVendor(String name){
        ContentValues values = new ContentValues();
        values.put(MySqlLiteHelper.VENDOR_NAME, name);
        database.insert(MySqlLiteHelper.VENDOR_TABLE, null, values);
    }

}
