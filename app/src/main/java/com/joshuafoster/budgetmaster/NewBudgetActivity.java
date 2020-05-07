package com.joshuafoster.budgetmaster;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Date;

public class NewBudgetActivity extends AppCompatActivity implements View.OnClickListener {
    String budgetName;
    Date startDate, endDate;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_date);

        b = findViewById(R.id.cancelButton);
        //set listeners

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
                onBackPressed();
                break;

            case R.id.nextButton:
                // Get values of entered data
                EditText et = findViewById(R.id.budgetET);
                budgetName = et.getText().toString();

                et = findViewById(R.id.startDateET);
                try {
                    startDate = MySqlLiteHelper.DATE_FORMAT_TEXT.parse(et.getText().toString());
                } catch (ParseException e) {
                    Log.e("Date", e.toString());
                }

                et = findViewById(R.id.endDateET);
                try {
                    endDate = MySqlLiteHelper.DATE_FORMAT_TEXT.parse(et.getText().toString());
                } catch (ParseException e) {
                    Log.e("Date", e.toString());
                }

                // Send values to the database
                DbDataSource dataSource = new DbDataSource(this);
                dataSource.openForWriting();
                dataSource.addBudget(budgetName, startDate, endDate);

                setContentView(R.layout.set_budget);

                Intent budgetSetIntent = new Intent(this, SetBudgetActivity.class);
                startActivity(budgetSetIntent);

                break;

        }
    }
}
