package com.example.autos.dao;

import com.example.autos.models.Car;
import com.example.autos.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {
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

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        try {
            String query = "SELECT * FROM User\n" +
                    "INNER JOIN Car C on User.id = C.user_id";
            Statement statement = connection.createStatement();
            ResultSet set = statement.executeQuery(query);
            set.next();
            int prev_id = Integer.parseInt(set.getString("id"));
            String email = set.getString("email");
            String password = set.getString("password");
            String fullname = set.getString("fullname");
            int age = set.getInt("age");
            User user = new User(prev_id, email, password, fullname, age);
            user.setCars(new ArrayList<>());
            user.getCars().add(new Car(
                    set.getString("model"),
                    set.getInt("year"),
                    user
            ));
            while(set.next()) {
                int cur_id = Integer.parseInt(set.getString("id"));
                if(cur_id != prev_id) {
                    users.add(user);
                    prev_id = cur_id;
                    email = set.getString("email");
                    password = set.getString("password");
                    fullname = set.getString("fullname");
                    age = set.getInt("age");
                    user = new User(prev_id, email, password, fullname, age);
                    user.setCars(new ArrayList<>());
                }
                Car car = new Car(
                        set.getString("model"),
                        set.getInt("year"),
                        user
                );
                user.getCars().add(car);
            }
            users.add(user);
        }
        catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return users;
    }

    public User getUser(int id) {
        try {
            String query = "SELECT * FROM User INNER JOIN Car ON Car.user_id = User.id WHERE User.id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();
            if(set.next()) {
                String email = set.getString("email");
                String password = set.getString("password");
                String fullName = set.getString("fullname");
                int age = set.getInt("age");
                User user = new User(id, email, password, fullName, age);
                List<Car> cars = new ArrayList<>();
                cars.add(new Car(set.getString("model"), set.getInt("year"), user));
                while(set.next()) {
                    cars.add(new Car(set.getString("model"), set.getInt("year"), user));
                }

                user.setCars(cars);
                return user;
            }

        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
