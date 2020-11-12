package spring.security.jwt.controller.dto;

import spring.security.jwt.bean.PatientCard;

import java.util.Date;

public class PatientVisitRequest {
    private Date date;

    private PatientCard patientCard;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PatientCard getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(PatientCard patientCard) {
        this.patientCard = patientCard;
    }
}
