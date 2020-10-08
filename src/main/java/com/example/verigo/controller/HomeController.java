package com.example.verigo.controller;

import com.example.verigo.bean.User;
import com.example.verigo.repos.UserRepo;
import com.example.verigo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Controller
public class HomeController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private UserService userService;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping(value = {"/home","/"})
    public String greeting(Model model) {

        model.addAttribute("users", userService.findAllPatient());
        return "home";
    }


    @PostMapping("/home")
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam("file") MultipartFile file,
            Map<String, Object> model) throws IOException {

        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "" + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFileName));

            user.setFilename(resultFileName);
        }

        userRepo.save(user);

        return "home";
    }


}