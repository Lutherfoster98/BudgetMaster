package com.joshuafoster.budgetmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.util.Date;

public class SetBudgetActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_budget);
        //set listeners

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancelButton:
                onBackPressed();
                break;
        }
    }
}
