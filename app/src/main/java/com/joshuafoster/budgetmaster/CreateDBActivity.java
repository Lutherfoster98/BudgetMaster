package com.joshuafoster.budgetmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class CreateDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String output = "Database opened for writing.";

        DbDataSource dataSource = new DbDataSource(this);
        dataSource.openForWriting();
        //TextView textView = findViewById(R.id.tv_createDB);
        Random random = new Random();

        //textView.setText(output);

        String[] vendors = {"MacDonalds", "Target", "Shell", "Chipotle", "Sonic"};
        String[] categories = {"Food", "Groceries", "Gas", "Food", "Food"};

        for (int i = 0; i < 10; i++) {
            int randomIndex = random.nextInt(5);
            dataSource.addTransaction(new Transaction(Calendar.getInstance().getTime(), vendors[randomIndex], random.nextDouble() * 100, categories[randomIndex]));
            output += "\nAdded " + i + " records";
        }
        //textView.setText(output);

        List<Transaction> newTransactions = dataSource.getAllTransactions();
        Log.i("Tracking", "Transactions retrieved: " + newTransactions.size());
        for (int i = 0; i < newTransactions.size(); i++){
            output += "\n Loaded: " + newTransactions.get(i).toString();
        }
        //textView.setText(output);
        dataSource.close();

    }
}
