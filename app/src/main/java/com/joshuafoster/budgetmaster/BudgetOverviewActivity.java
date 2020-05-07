package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class BudgetOverviewActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_overview);

        //set listeners
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
        }
    }
}
