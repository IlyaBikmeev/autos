package com.example.autos.models;

public class Car {
    private String model;
    private int year;
    private User user;

    public Car(String model, int year, User user) {
        this.model = model;
        this.year = year;
        this.user = user;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
