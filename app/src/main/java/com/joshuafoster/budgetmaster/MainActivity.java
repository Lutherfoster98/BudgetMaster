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
        insertSampleData();
    }

    private void insertSampleData(){
        final int LIMIT = 10;
        Log.i("Database", "Database opened for writing.");

        DbDataSource dataSource = new DbDataSource(this);
        dataSource.openForWriting();
        Random random = new Random();

        String[] vendors = {"MacDonalds", "Target", "Shell", "Chipotle", "Sonic"};
        String[] categories = {"Food", "Groceries", "Gas", "Food", "Food"};

        for (int i = 0; i < LIMIT; i++) {
            int randomIndex = random.nextInt(5);
            dataSource.addTransaction(new Transaction(Calendar.getInstance().getTime(), random.nextDouble() * 100, randomIndex, vendors[randomIndex], randomIndex, categories[randomIndex], "Test data"));
            Log.i("Database", "Added " + i + " records");
        }

        for (int i = 0; i < vendors.length; i++) {
            dataSource.addVendor(vendors[i]);
            dataSource.addCategory(categories[i], "Test data");
        }

        List<Transaction> newTransactions = dataSource.getAllTransactions();
        Log.i("Database", "Transactions retrieved: " + newTransactions.size());
        for (int i = 0; i < newTransactions.size(); i++){
            Log.i("Database", "Loaded: " + newTransactions.get(i).toString());
        }




        dataSource.close();

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
                setContentView(R.layout.new_income);
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
