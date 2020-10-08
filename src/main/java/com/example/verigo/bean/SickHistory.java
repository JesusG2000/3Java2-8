package com.example.verigo.bean;

import com.example.verigo.bean.dto.Sick;

import javax.persistence.*;

@Entity
@Table(name = "patient_sick_history")
public class SickHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Enumerated(EnumType.STRING)
    private Sick sick;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientCard_id")
    private PatientCard patientCard;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Sick getSick() {
        return sick;
    }

    public void setSick(Sick sick) {
        this.sick = sick;
    }

    public PatientCard getPatientCard() {
        return patientCard;
    }

    public void setPatientCard(PatientCard patientCard) {
        this.patientCard = patientCard;
    }
}
