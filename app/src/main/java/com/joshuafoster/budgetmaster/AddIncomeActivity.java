package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddIncomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<Vendor> vendors = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    DbDataSource dataSource = new DbDataSource(this);

    private TextView dateTv;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income);
        dataSource.openForReading();
        vendors = dataSource.getAllVendors(MySqlLiteHelper.INCOME_TYPE);
        categories = dataSource.getAllCategories(MySqlLiteHelper.INCOME_TYPE);
        dataSource.close();
        typeCheck();

        // Spinner elements
        Spinner cat_spinner = findViewById(R.id.income_category_spinner);
        Spinner vendor_spinner = findViewById(R.id.income_vendor_spinner);

        // Spinner click listener
        cat_spinner.setOnItemSelectedListener(this);
        vendor_spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> category_names = new ArrayList<String>();
        List<String> vendor_names = new ArrayList<String>();
        for (int i = 0; i <  categories.size(); i++)
            category_names.add(categories.get(i).getName());
        for (int i = 0; i < vendors.size(); i++)
            vendor_names.add(vendors.get(i).getName());

        // Creating adapter for spinner
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, category_names);
        ArrayAdapter<String> vendorAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, vendor_names);

        // Drop down layout style - list view with radio button
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        vendorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        cat_spinner.setAdapter(categoryAdapter);
        vendor_spinner.setAdapter(vendorAdapter);


        dateTv = findViewById(R.id.dateET);
        dateTv.setInputType(InputType.TYPE_NULL);

        dateTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);

                DatePickerDialog dpd = new DatePickerDialog(
                        AddIncomeActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year,month,day);
                dpd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dpd.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month+=1;
                String dateChosen = "";
                if (month < 10)
                    dateChosen = "0" + month;
                dateChosen += "-";
                if (dayOfMonth < 10)
                    dateChosen += "0";
                dateChosen += dayOfMonth + "-" + year;
                dateTv.setText(dateChosen);
            }
        };





    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    public void typeCheck(){
        String str;
        TextView tv = findViewById(R.id.incomeHeadingTV);
        Bundle extras = getIntent().getExtras();
        str = extras.getString("title");

            tv.setText("Add "+str);
        tv = findViewById(R.id.payeeTV);
            if (str.equals("Expense")){
                tv.setText("Payee");
            }

    }

    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cancelButton:
                finish();
                break;
            case R.id.saveIncomeButton:

                break;
        }
    }
}
