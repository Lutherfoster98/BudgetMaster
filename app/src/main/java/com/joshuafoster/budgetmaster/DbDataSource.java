package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.joshuafoster.budgetmaster.MySqlLiteHelper;

import java.lang.reflect.Array;
import java.util.Date;
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
                transaction.setAmount(cursor.getDouble(cursor.getColumnIndex(MySqlLiteHelper.TRANS_AMOUNT)));
                transaction.setDescription(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.TRANS_DESCRIPTION)));
                transaction.setVendor_id(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.VENDOR_ID)));
                transaction.setVendor_name(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.VENDOR_NAME)));
                transaction.setCat_id(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.CAT_ID)));
                transaction.setCat_name(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.CAT_NAME)));
                // Adding contact to list
                transactions.add(transaction);
            } while (cursor.moveToNext());
        }

        return transactions;
    }

    public Transaction getTransaction(int id) {
        Transaction transaction = new Transaction();
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
                MySqlLiteHelper.CATEGORY_TABLE + "." + MySqlLiteHelper.CAT_ID + "WHERE " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.TRANS_ID + " = " + id + ";";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                transaction.setId(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.TRANS_ID)));
                try {
                    transaction.setDate(dateFormat.parse(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.TRANS_DATE))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                transaction.setAmount(cursor.getDouble(cursor.getColumnIndex(MySqlLiteHelper.TRANS_AMOUNT)));
                transaction.setDescription(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.TRANS_DESCRIPTION)));
                transaction.setVendor_id(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.VENDOR_ID)));
                transaction.setVendor_name(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.VENDOR_NAME)));
                transaction.setCat_id(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.CAT_ID)));
                transaction.setCat_name(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.CAT_NAME)));
                // Adding contact to list
            } while (cursor.moveToNext());
        }

        return transaction;
    }


    public List<Vendor> getAllVendors(String type) {
        List<Vendor> vendors = new ArrayList<>();

        String query = "SELECT * FROM " + MySqlLiteHelper.VENDOR_TABLE + " WHERE " +
                MySqlLiteHelper.VENDOR_TYPE + " = \"" + type + "\";";
        Cursor cursor = database.rawQuery(query, null);
        Log.i("Tracking", "Vendor rows returned: " + cursor.getCount());

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Vendor vendor = new Vendor();
                vendor.setId(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.VENDOR_ID)));
                vendor.setName(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.VENDOR_NAME)));
                // Adding contact to list
                vendors.add(vendor);
            } while (cursor.moveToNext());
        }

        return vendors;
    }

    public List<Category> getAllCategories(String type) {
        List<Category> categories = new ArrayList<>();

        String query = "SELECT * FROM " + MySqlLiteHelper.CATEGORY_TABLE + " WHERE " +
                MySqlLiteHelper.CAT_TYPE + " = \"" + type + "\";";
        Cursor cursor = database.rawQuery(query, null);
        Log.i("Tracking", "Category rows returned: " + cursor.getCount());

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Category category = new Category();
                category.setId(cursor.getInt(cursor.getColumnIndex(MySqlLiteHelper.CAT_ID)));
                category.setName(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.CAT_NAME)));
                category.setDescription(cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.CAT_DESCRIPTION)));
                // Adding contact to list
                categories.add(category);
            } while (cursor.moveToNext());
        }

        return categories;
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

    int addBudget(String name, Date start, Date end){
        ContentValues values = new ContentValues();
        values.put(MySqlLiteHelper.BUDGET_NAME, name);
        values.put(MySqlLiteHelper.BUDGET_START, MySqlLiteHelper.DATE_FORMAT_DB.format(start));
        values.put(MySqlLiteHelper.BUDGET_END, MySqlLiteHelper.DATE_FORMAT_DB.format(end));
        return (int) database.insert(MySqlLiteHelper.BUDGET_TABLE, null, values);
    }

    void addBudgeted(int budget_id, int cat_id, double amount){
        ContentValues values = new ContentValues();
        values.put(MySqlLiteHelper.BUDGET_ID, budget_id);
        values.put(MySqlLiteHelper.CAT_ID, cat_id);
        values.put(MySqlLiteHelper.BUDGETED_AMOUNT, amount);
        database.insert(MySqlLiteHelper.BUDGETED_TABLE, null, values);
    }
    public List<String> getBudgeted(int budget_id){
        List<String> Transactions = new ArrayList<>();

        String query = "SELECT * FROM " + MySqlLiteHelper.BUDGETED_TABLE + " WHERE " +
                MySqlLiteHelper.BUDGET_ID + " = \"" + budget_id + "\";";
        Cursor cursor = database.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                String st = new String();
                st=(MySqlLiteHelper.CAT_ID)+
                (MySqlLiteHelper.BUDGETED_AMOUNT);
                // Adding contact to list
                Transactions.add(st);
            } while (cursor.moveToNext());
        }
        return Transactions;
    }
}
