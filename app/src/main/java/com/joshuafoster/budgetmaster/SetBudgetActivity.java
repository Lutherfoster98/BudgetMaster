package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SetBudgetActivity extends AppCompatActivity implements View.OnClickListener {

    String name;
    Date startDate, endDate;
    int budget_id;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        name = intent.getStringExtra("budgetName");
        try {
            startDate = MySqlLiteHelper.DATE_FORMAT_DB.parse(intent.getStringExtra("startDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        try {
            endDate = MySqlLiteHelper.DATE_FORMAT_DB.parse(intent.getStringExtra("endDate"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        setContentView(R.layout.set_budget);
        //set listeners

        Button temp;
        temp = findViewById(R.id.setBudgetNextButton);
        temp.setOnClickListener(this);
        temp = findViewById(R.id.setBudgetCancelButton);
        temp.setOnClickListener(this);

        //populate listview
        listView = findViewById(R.id.category_listview);
        DbDataSource dataSource = new DbDataSource(this);
        dataSource.openForReading();
        // Query for items from the database and get a cursor back
        String query = "SELECT " + MySqlLiteHelper.CAT_ID + " as _id, " +
                MySqlLiteHelper.CAT_NAME + ", " +
                MySqlLiteHelper.CAT_TYPE + " FROM " + MySqlLiteHelper.CATEGORY_TABLE + " ORDER BY " +
                MySqlLiteHelper.CAT_TYPE + ";";
        Cursor cursor = dataSource.getCursor(query);
        Log.i("Database", "Query: " + query);

        if (cursor.moveToFirst()) {
            listView.setAdapter(new CategoriesCursorAdapter(this, cursor));
        }
        dataSource.close();


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.setBudgetCancelButton:
                finish();
                break;
            case R.id.setBudgetNextButton:
                DbDataSource database = new DbDataSource(this);
                database.openForWriting();
                budget_id = database.addBudget(name, startDate, endDate);
                for (int i = 0; i < listView.getChildCount(); i++){
                    View listViewLayout = listView.getChildAt(i);
                    TextView textView = listViewLayout.findViewById(R.id.tv_cat_id);
                    int id = Integer.valueOf(textView.getText().toString());
                    EditText editText = listViewLayout.findViewById(R.id.tv_cat_amount);
                    double amount = Double.valueOf(editText.getText().toString());
                    database.addBudgeted(budget_id, id, amount);
                }
                setContentView(R.layout.activity_main);
                break;
        }
    }
}
