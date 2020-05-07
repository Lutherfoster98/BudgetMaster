package com.joshuafoster.budgetmaster;

// Team Members: Lionel Sosa Estrada, Joshua Foster, and Stephanie Escue

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Transaction {

    private int id;
    private int vendor_id;
    private int cat_id;
    private double amount;
    private Date date;
    private String vendor_name;
    private String cat_name;
    private String description;

    public Transaction(){

    }

    public Transaction(Date date, double amount, int vendor_id, String vendor_name, int cat_id, String cat_name, String description){
        this.setDate(date);
        this.setAmount(amount);
        this.setVendor_id(vendor_id);
        this.setVendor_name(vendor_name);
        this.setCat_id(cat_id);
        this.setCat_name(cat_name);
        this.setDescription(description);
    }

    public Transaction(int id, Date date, double amount, int vendor_id, String vendor_name, int cat_id, String cat_name){
        this.setId(id);
        this.setDate(date);
        this.setAmount(amount);
        this.setVendor_id(vendor_id);
        this.setVendor_name(vendor_name);
        this.setCat_id(cat_id);
        this.setCat_name(cat_name);
        this.setDescription(description);
    }



    @Override
    public String toString(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-YYYY", Locale.ENGLISH);
        return dateFormat.format(getDate()) + " " + vendor_name + ", $" + getAmount();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
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

    public String getVendor_name() {
        return vendor_name;
    }

    public void setVendor_name(String vendor_name) {
        this.vendor_name = vendor_name;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String category_name) {
        this.cat_name = category_name;
    }

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}
