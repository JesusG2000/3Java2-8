package com.example.verigo.service;

import com.example.verigo.bean.User;
import com.example.verigo.bean.dto.Role;
import com.example.verigo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username);
    }

    public List<User> findAllPatient(){
        List<User> patients = new LinkedList<>();
        for (User u  :userRepo.findAll()){
            if(u.getRoles().contains(Role.PATIENT)){
                patients.add(u);
            }
        }
        return patients;
    }

}
