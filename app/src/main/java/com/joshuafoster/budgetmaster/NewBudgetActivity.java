package com.joshuafoster.budgetmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class NewBudgetActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_budget);

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
        }
    }
}
