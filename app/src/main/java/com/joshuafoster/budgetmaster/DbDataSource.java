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

        String selectQuery = "SELECT * FROM " + databaseHelper.TRANSACTION_TABLE;

        Cursor cursor = database.rawQuery(selectQuery, null);
        Log.i("Tracking", "Rows returned: " + cursor.getCount());

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Transaction transaction = new Transaction();
                transaction.setId(cursor.getInt(0));
                try {
                    transaction.setDate(dateFormat.parse(cursor.getString(1)));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                transaction.setVendor(cursor.getString(2));
                transaction.setAmount(cursor.getDouble(3));
                transaction.setCategory(cursor.getString(4));
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
        values.put(MySqlLiteHelper.DATE, dateFormat.format(transaction.getDate())); // Contact Name
        values.put(MySqlLiteHelper.VENDOR, transaction.getVendor()); // Contact Phone
        values.put(MySqlLiteHelper.AMOUNT, String.format("%.2f", transaction.getAmount()));
        values.put(MySqlLiteHelper.CATEGORY, transaction.getCategory());

        // Inserting Row
        database.insert(MySqlLiteHelper.TRANSACTION_TABLE, null, values);
        //2nd argument is String containing nullColumnHack
        Log.i("Tracking", "Added record: " + transaction.getVendor() + " $" + transaction.getAmount());
    }
}
