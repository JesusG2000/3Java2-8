package spring.security.jwt.controller.dto;

import spring.security.jwt.bean.User;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DoctorDocumentRequest {
    @Size(min = 2, max = 16, message = "name from 2 to 16")
    @NotNull(message = "Can not be null")
    private String name;

    @Size(min = 4, max = 16, message = "surname from 4 to 16")
    @NotNull(message = "Can not be null")
    private String surname;

    @Size(min = 4, max = 16, message = "fathername from 4 to 16")
    @NotNull(message = "Can not be null")
    private String fathername;

    private int userId;

    private String doctorSpec;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFathername() {
        return fathername;
    }

    public void setFathername(String fathername) {
        this.fathername = fathername;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDoctorSpec() {
        return doctorSpec;
    }

    public void setDoctorSpec(String doctorSpec) {
        this.doctorSpec = doctorSpec;
    }
}
