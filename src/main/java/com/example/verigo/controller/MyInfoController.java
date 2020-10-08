package com.example.verigo.controller;

import com.example.verigo.bean.DoctorDocument;
import com.example.verigo.bean.PatientCard;
import com.example.verigo.bean.User;
import com.example.verigo.bean.dto.Role;
import com.example.verigo.repos.DoctorDocumentRepo;
import com.example.verigo.repos.PatientCardRepo;
import com.example.verigo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/info")
public class MyInfoController {


    @Autowired
    private PatientCardRepo patientCardRepo;
    @Autowired
    private DoctorDocumentRepo doctorDocumentRepo;

    @GetMapping
    public String show(
            @AuthenticationPrincipal User user,
            Model model) {
        System.out.println(user.getId());
        if (user.getRoles().contains(Role.PATIENT)) {
            Optional<PatientCard> result = patientCardRepo.findById(user.getId());
            if (result.isPresent()) {
                model.addAttribute("patient", result);
            } else {
                model.addAttribute("patient", null);
            }
        } else {
            Optional<DoctorDocument> result = doctorDocumentRepo.findById(user.getId());
            if (result.isPresent()) {
                model.addAttribute("doctor", result.get());
            } else {
                model.addAttribute("doctor", null);
            }


        }


        return "myInfo";
    }
}
