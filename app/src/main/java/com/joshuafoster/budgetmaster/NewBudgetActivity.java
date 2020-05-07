package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;

public class NewBudgetActivity extends AppCompatActivity implements View.OnClickListener {
    String budgetName;
    Date startDate, endDate;
    Button b;
    Calendar cal = Calendar.getInstance();
    private TextView startDateTv, endDateTv;
    private DatePickerDialog.OnDateSetListener startDateListener,endDateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_budget);

        b = findViewById(R.id.cancelButton);
        //set listeners
        startDateTv = findViewById(R.id.startDateET);
        startDateTv.setInputType(InputType.TYPE_NULL);

        endDateTv = findViewById(R.id.endDateET);
        endDateTv.setInputType(InputType.TYPE_NULL);

        endDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);

                DatePickerDialog dpd = new DatePickerDialog(
                        NewBudgetActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        endDateListener,
                        year,month,day);
                dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dpd.show();
            }
        });
        startDateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);

                DatePickerDialog dpd = new DatePickerDialog(
                        NewBudgetActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        startDateListener,
                        year,month,day);
                dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dpd.show();
            }
        });


        startDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String dateChosen = "";
                if (month < 10)
                    dateChosen = "0" + month;
                dateChosen += "-";
                if (dayOfMonth < 10)
                    dateChosen += "0";
                dateChosen += dayOfMonth + "-" + year;
                startDateTv.setText(dateChosen);
            }
        };


        endDateListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month += 1;
                String dateChosen = "";
                if (month < 10)
                    dateChosen = "0" + month;
                dateChosen += "-";
                if (dayOfMonth < 10)
                    dateChosen += "0";
                dateChosen += dayOfMonth + "-" + year;
                endDateTv.setText(dateChosen);
            }
        };
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.newBudgetCancelButton:
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

                Intent budgetSetIntent = new Intent(this, SetBudgetActivity.class);
                budgetSetIntent.putExtra("budgetName", budgetName);
                budgetSetIntent.putExtra("startDate", MySqlLiteHelper.DATE_FORMAT_DB.format(startDate));
                budgetSetIntent.putExtra("endDate", MySqlLiteHelper.DATE_FORMAT_DB.format(endDate));
                startActivity(budgetSetIntent);
                break;
        }


    }
}