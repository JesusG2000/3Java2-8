package com.example.verigo.controller;

import com.example.verigo.bean.dto.Role;
import com.example.verigo.bean.User;
import com.example.verigo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Map<String, Object> model) {
        if (isValidateUser(user)) {

            User userFormBD = userRepo.findByUsername(user.getUsername());

            if (userFormBD != null) {
                model.put("message", "User exist!");
                return "registration";
            }
            user.setRoles(Collections.singleton(Role.PATIENT));
            userRepo.save(user);

        } else {
            model.put("message", "Wrong pattern(consist of 3 to 16 symbols) of login/password ");
            return "registration";
        }
        return "redirect:/login";
    }

    private boolean isValidateUser(User user) {
        String name = user.getUsername().trim();
        String pass = user.getPassword().trim();

        if (name.isEmpty() || pass.isEmpty()) {
            return false;
        }
        int nameL = name.length();
        int passL = pass.length();

        if (nameL > 16 || nameL < 3) {
            return false;
        }
        if (passL > 16 || passL < 3) {
            return false;
        }
        user.setUsername(name);
        user.setPassword(pass);
        return true;
    }
}
