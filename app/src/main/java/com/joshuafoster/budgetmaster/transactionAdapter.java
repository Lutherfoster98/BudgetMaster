package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

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

public class transactionAdapter extends CursorAdapter {
    public transactionAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.transaction_layout, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        Date date = new Date();
        final String SHORT_DATE_FORMAT = "MM-dd-yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(SHORT_DATE_FORMAT, Locale.US);
        // Find fields to populate in inflated template
        TextView tvDate = view.findViewById(R.id.tv_date);
        TextView tvVendor = view.findViewById(R.id.tv_vendor);
        TextView tvAmount = view.findViewById(R.id.tv_amount);

        // Extract properties from cursor
        try {
            date = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("date")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String vendor = cursor.getString(cursor.getColumnIndexOrThrow("vendor"));
        Double amount = cursor.getDouble(cursor.getColumnIndexOrThrow("amount"));
        // Populate fields with extracted properties

        tvDate.setText(date.toString());
    }
}
