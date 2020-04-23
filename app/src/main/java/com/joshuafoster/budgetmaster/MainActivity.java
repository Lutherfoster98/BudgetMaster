package com.joshuafoster.budgetmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
                setContentView(R.layout.expenses_overview);
                break;
            case R.id.editSavingsBtn:
                setContentView(R.layout.savings_overview);
                break;
        }

    }
}
