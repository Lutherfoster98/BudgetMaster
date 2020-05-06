package com.joshuafoster.budgetmaster;

public class Category {

    private int id;
    private String name, description;

    public Category(){

    }

    public Category(String name, String description){
        this.setName(name);
        this.setDescription(description);
    }

    public Category(int id, String name, String description){
        this.setId(id);
        this.setName(name);
        this.setDescription(description);
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

    public String getDescription(){
        return description;
    }

    public void setDescription(String description){
        this.description = description;
    }
}