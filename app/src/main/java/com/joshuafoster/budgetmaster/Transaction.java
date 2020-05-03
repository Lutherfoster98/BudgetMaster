package com.joshuafoster.budgetmaster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transaction {

    private int id;
    private double amount;
    private Date date;
    private String vendor, category;

    public Transaction(){

    }

    public Transaction(Date date, String vendor, double amount, String category){
        this.date = date;
        this.vendor = vendor;
        this.amount = amount;
        this.category = category;
    }

    public Transaction(int id, Date date, String vendor, double amount, String category){
        this.id = id;
        this.date = date;
        this.vendor = vendor;
        this.amount = amount;
        this.category = category;
    }


    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-YYYY", Locale.ENGLISH);
        return dateFormat.format(date) + " " + vendor + ", $" + amount;
    }

}
