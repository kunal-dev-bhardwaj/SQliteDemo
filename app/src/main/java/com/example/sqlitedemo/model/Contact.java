package com.example.sqlitedemo.model;

public class Contact {
    private int id;
    private String name;
    private String phoneNumber;


    public Contact(String name, String phoneNumber) { //if id is not their
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    public Contact(int id,String name,String phoneNumber){ //if id is their
        this.id=id;
        this.name=name;
        this.phoneNumber=phoneNumber;
    }

    public Contact(){ //if no values are their

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
