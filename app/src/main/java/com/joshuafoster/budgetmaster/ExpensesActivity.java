package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
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
        String query = "SELECT " + MySqlLiteHelper.TRANS_ID + " AS _id, " +
                MySqlLiteHelper.TRANS_DATE + ", " +
                MySqlLiteHelper.VENDOR_NAME + ", " +
                MySqlLiteHelper.TRANS_AMOUNT + " FROM " +
                MySqlLiteHelper.TRANSACTION_TABLE + " INNER JOIN " +
                MySqlLiteHelper.VENDOR_TABLE + " ON " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.VENDOR_ID + " = " +
                MySqlLiteHelper.VENDOR_TABLE + "." + MySqlLiteHelper.VENDOR_ID + " WHERE " +
                MySqlLiteHelper.TRANSACTION_TABLE + "." + MySqlLiteHelper.TRANS_AMOUNT + " < 0;";
        Cursor cursor = dataSource.getCursor(query);
        Log.i("Database", "Query: " + query);

        if (cursor.moveToFirst()) {
            listView.setAdapter(new ExpensesCursorAdapter(this, cursor));
        }
        dataSource.close();
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }


    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancelButton:
                finish();
                break;
            case R.id.saveExpenseButton:

                break;
        }
    }
}
