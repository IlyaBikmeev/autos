package com.example.autos.controllers;

import com.example.autos.dao.UserDAO;
import com.example.autos.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UserDAO userDAO;


    @GetMapping
    public String allUsers(Model model) {
        List<User> users = userDAO.allUsers();
        users.forEach(e -> System.out.println(e.getEmail()));
        model.addAttribute("users", users);
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable int id, Model model) {
        User user = userDAO.getUser(id);
        model.addAttribute("driver", user);
        return "users/show";
    }

}
