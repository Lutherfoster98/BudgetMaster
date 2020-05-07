package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class BudgetOverviewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_overview);

        //set listeners
        Button temporary;
        temporary=findViewById(R.id.incomeTransactionsBtn);
        temporary.setOnClickListener(this);
        temporary=findViewById(R.id.expenseTransactionsBtn);
        temporary.setOnClickListener(this);
        temporary=findViewById(R.id.viewBudgetBtn);
        temporary.setOnClickListener(this);
        temporary=findViewById(R.id.budgetOverviewCancelButton);
        temporary.setOnClickListener(this);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.incomeTransactionsBtn:
                Intent incomeIntent = new Intent(this, IncomeActivity.class);
                startActivity(incomeIntent);
                break;
            case R.id.expenseTransactionsBtn:
                Intent intent = new Intent(this, ExpensesActivity.class);
                startActivity(intent);
                break;
            case R.id.viewBudgetBtn:
                Intent viewBudgetIntent = new Intent(this, ShowBudgetActivity.class);
                startActivity(viewBudgetIntent);
                break;
            case R.id.budgetOverviewCancelButton:
                finish();
                break;
        }
    }
}
