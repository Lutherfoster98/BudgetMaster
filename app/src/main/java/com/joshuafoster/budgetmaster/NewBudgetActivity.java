package com.joshuafoster.budgetmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

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
            case R.id.editIncomeBtn:
                setContentView(R.layout.income_overview);
                break;
            case R.id.editExpensesBtn:
                Intent intent = new Intent(this, ExpensesActivity.class);
                startActivity(intent);
                break;
            case R.id.editSavingsBtn:
                setContentView(R.layout.savings_overview);
                break;
            case R.id.cancelButton:
                onBackPressed();
                break;

            case R.id.nextButton:
                /*
                // Get values of entered data
                EditText et = findViewById(R.id.budgetET);
                budgetName = et.getText().toString());

                EditText et2 = findViewById(R.id.startDateET);
                startDate = et2.getText();

                EditText et3 = findViewById(R.id.endDateET);
                endDate = et3.getText().toString();

                // Send values to the database
                DbDataSource dataSource = new DbDataSource(this);
                dataSource.openForWriting();
                dataSource.addBudget(Date )


                setContentView(R.layout.set_budget); */
                break;

        }
    }
}
