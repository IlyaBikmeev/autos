package com.example.autos.dao;

import com.example.autos.models.Car;
import com.example.autos.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CarDAO {
    private static Connection connection;

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:D:\\java projects\\autos\\database.db");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Car> allCars() {
        List<Car> cars = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT * FROM Car INNER JOIN User ON User U on Car.user_id = U.id";
            ResultSet set = statement.executeQuery(query);
            while(set.next()) {
                String model = set.getString("model");
                int year = set.getInt("year");
                int user_id = set.getInt("user_id");
                User user = new User(user_id,
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("fullname"),
                        set.getInt("age"));

                Car car = new Car(model, year, user);
                cars.add(car);
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return cars;
    }

    public Car getCar(int id) {
        try {
            String query = "SELECT * FROM Car INNER JOIN User ON User U on Car.user_id = U.id WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                int carID = set.getInt("id");
                String model = set.getString("model");
                int year = set.getInt("year");
                int user_id = set.getInt("user_id");
                User user = new User(user_id,
                        set.getString("email"),
                        set.getString("password"),
                        set.getString("fullname"),
                        set.getInt("age"));
                return new Car(model, year, user);
            }
        }
        catch(SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
