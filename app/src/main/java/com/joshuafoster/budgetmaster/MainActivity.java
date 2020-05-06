package com.joshuafoster.budgetmaster;

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
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Button ViewButton = findViewById(R.id.viewBudgetButton);
        ViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.budget_overview);
                setOverviewListeners();
            }
        });
    }

    //set listeners for Budget_overview.xml
    private void setOverviewListeners() {
        Button temporary;
        temporary=findViewById(R.id.editIncomeBtn);
        temporary.setOnClickListener(this);
        temporary=findViewById(R.id.editExpensesBtn);
        temporary.setOnClickListener(this);
        temporary=findViewById(R.id.editSavingsBtn);
        temporary.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.editIncomeBtn:
                setContentView(R.layout.income_overview);
                break;
            case R.id.editExpensesBtn:
                Intent intent = new Intent(this, ExpensesActivity.class);
                startActivity(intent);
                setContentView(R.layout.expenses_overview);
                break;
            case R.id.editSavingsBtn:
                setContentView(R.layout.savings_overview);
                break;

            // Buttons on home page
            case R.id.createNewBudgetButton:
                setContentView(R.layout.set_budget);
                break;
            case R.id.plusSignImageView:
                Intent addIncomeIntent = new Intent(this, AddIncomeActivity.class);
                startActivity(addIncomeIntent);
                break;
            case R.id.minusSignImageView:
                setContentView(R.layout.new_expense);
                break;
        }

    }

    /*class displayCalendar() {
        final TextView startDate, endDate;
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
