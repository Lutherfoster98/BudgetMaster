package com.joshuafoster.budgetmaster;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class AddIncomeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    List<Vendor> vendors = new ArrayList<>();
    List<Category> categories = new ArrayList<>();
    DbDataSource dataSource = new DbDataSource(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_income);
        dataSource.openForReading();
        vendors = dataSource.getAllVendors();
        categories = dataSource.getAllCategories();
        dataSource.close();

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

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
