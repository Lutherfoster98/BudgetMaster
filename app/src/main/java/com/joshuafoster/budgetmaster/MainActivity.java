package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbDataSource startDB = new DbDataSource(this);
        Button temp = findViewById(R.id.viewBudgetButton);
        temp.setOnClickListener(this);
        temp = findViewById(R.id.createNewBudgetButton);
        temp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            // Buttons on home page
            case R.id.viewBudgetButton:
                Intent budgetOverviewIntent = new Intent(this, BudgetOverviewActivity.class);
                startActivity(budgetOverviewIntent);
                break;
            case R.id.createNewBudgetButton:
                Intent newBudgetIntent = new Intent(this, NewBudgetActivity.class);
                startActivity(newBudgetIntent);
                break;
            case R.id.plusSignImageView:
                Intent addIncomeIntent = new Intent(this, AddIncomeActivity.class);
                addIncomeIntent.putExtra("title","Income");
                startActivity(addIncomeIntent);
                break;
            case R.id.minusSignImageView:
                Intent addExpenseIntent = new Intent(this, AddIncomeActivity.class);
                addExpenseIntent.putExtra("title","Expense");
                startActivity(addExpenseIntent);                break;
        }

    }

    /* private void pickDate () {
        final EditText startDate, endDate;
        DatePickerDialog.OnDateSetListener DateSetListener = null;
            startDate = findViewById(R.id.startDateET);
            endDate = findViewById(R.id.endDateET);
            startDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Calendar cal = Calendar.getInstance();
                    int year = cal.get(Calendar.YEAR);
                    int month = cal.get(Calendar.MONTH);
                    int day = cal.get(Calendar.DAY_OF_MONTH);
                    DatePickerDialog dialog = new DatePickerDialog(
                            MainActivity.this,
                            android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                            DateSetListener,
                            year,month,day);
                    dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    dialog.show();
                }
            });
            DateSetListener = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                    month = month + 1;
                    Log.d("DATE", "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);
                    String date = month + "/" + day + "/" + year;
                    startDate.setText(date);
                }
            };
        }
    }
    } */
    }


