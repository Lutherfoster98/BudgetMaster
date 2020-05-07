package com.joshuafoster.budgetmaster;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class CategoriesCursorAdapter extends CursorAdapter {

    Context context;

    public CategoriesCursorAdapter(Context context, Cursor cursor) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.category_layout, parent, false); //layout goes here
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        this.context = context;
        TextView tv_category = view.findViewById(R.id.tv_category);
        TextView tv_cat_id = view.findViewById(R.id.tv_cat_id);
        TextView tv_cat_amount = view.findViewById(R.id.tv_cat_amount);
        // Extract properties from cursor
        String category = cursor.getString(cursor.getColumnIndex(MySqlLiteHelper.CAT_NAME));
        int id = cursor.getInt(cursor.getColumnIndex("_id"));
        // Populate fields with extracted properties
        tv_category.setText(category);
        tv_cat_id.setText(String.valueOf(id));
    }

    /*private class ViewHolder {
        TextView category;
        TextView id;
        EditText amount;
    }*/

    /*@Override
    public View getView(final int position, View convertView, final ViewGroup parent){
        final ViewHolder viewHolder = new ViewHolder();

        View v = convertView;
        if (v == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            v = inflater.inflate(R.layout.category_layout, null);
            viewHolder.category = v.findViewById(R.id.tv_cat_amount);
            viewHolder.id = v.findViewById(R.id.tv_cat_id);
            viewHolder.amount = v.findViewById(R.id.tv_cat_amount);
            v.setTag(viewHolder);
        } else {
            v.setTag(convertView.getTag());
        }
        return v;
    }*/
}
