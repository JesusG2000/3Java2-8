package com.example.verigo.bean.dto;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    PATIENT, DOCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
