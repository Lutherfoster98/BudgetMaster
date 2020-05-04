package com.joshuafoster.budgetmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class ExpensesActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expenses_overview);

        ListView listView = findViewById(R.id.expenses_list_view);
        listView.setOnItemClickListener(this);

        DbDataSource dataSource = new DbDataSource(this);
        dataSource.openForReading();
        // Query for items from the database and get a cursor back
        Cursor cursor = dataSource.getCursor("SELECT  id AS _id, date, vendor, amount FROM Transactions");

        if (cursor.moveToFirst()) {
            ExpensesCursorAdapter adapter = new ExpensesCursorAdapter(this, cursor);
            listView.setAdapter(adapter);
        }
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
