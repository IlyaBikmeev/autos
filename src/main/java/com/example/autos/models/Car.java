package com.example.autos.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Car {
    private String model;
    private int year;
    @ToString.Exclude
    private User user;
}
