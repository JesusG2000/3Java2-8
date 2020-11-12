package spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import spring.security.jwt.service.impl.UserServiceImpl;

@Controller
public class MainController {
    @Autowired
    private UserServiceImpl userService;
    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/activate/{code}")
    public String activate(@PathVariable String code){
        boolean isActivated = userService.activateUser(code);
        System.out.println(isActivated);
        return "login";
    }
}
