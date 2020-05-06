package com.joshuafoster.budgetmaster;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Vendor {

    private int id;
    private String name, type;

    public Vendor(){

    }

    public Vendor(String name){
        this.setName(name);
    }

    public Vendor(int id, String name){
        this.setId(id);
        this.setName(name);
    }

    @Override
    public String toString(){
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

}


