package com.example.autos.models;

import lombok.*;

import java.util.List;
/*
Для данного класса будут созданы публичные геттеры и сеттеры для всех полей.
Также будет создан пустой конструктор по умолчанию.
Также будет создан конструктор из полей, которые помечены как @NonNull.
 */
@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class User {
    @NonNull
    private int id;
    @NonNull
    private String email;
    @NonNull
    private String password;
    @NonNull
    private String fullName;
    @NonNull
    private int age;
    private List<Car> cars;
}
