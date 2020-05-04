package com.joshuafoster.budgetmaster;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ExpensesCursorAdapter extends CursorAdapter {

    public ExpensesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.transaction_layout, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView tv_date = view.findViewById(R.id.tv_date);
        TextView tv_vendor = view.findViewById(R.id.tv_vendor);
        TextView tv_amount = view.findViewById(R.id.tv_amount);
        // Extract properties from cursor
        SimpleDateFormat parseDateFormat = new SimpleDateFormat("EEE MMM dd kk:mm:ss z yyyy", Locale.ENGLISH);
        SimpleDateFormat writeDateFormat = new SimpleDateFormat("MM-dd-yy", Locale.ENGLISH);
        Date date = new Date();
        try {
            date = parseDateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String vendor = cursor.getString(cursor.getColumnIndexOrThrow("vendor"));
        Double amount = cursor.getDouble(cursor.getColumnIndexOrThrow("amount"));
        // Populate fields with extracted properties
        tv_date.setText(writeDateFormat.format(date));
        tv_vendor.setText(vendor);
        tv_amount.setText("$" + String.valueOf(amount));
    }
}
