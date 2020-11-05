package spring.security.jwt.bean;

import lombok.Data;
import spring.security.jwt.bean.dto.Sick;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "patient_card")
@Data
public class PatientCard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String surname;
    private String fathername;
    private String recommendation;
    private String patientReport;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private User patient;

    @ElementCollection(targetClass = Sick.class , fetch = FetchType.EAGER)
    @CollectionTable(name = "sick" , joinColumns = @JoinColumn(name = "patient_id"))
    @Enumerated(EnumType.STRING)
    private Set<Sick> sicks;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }

    public Set<Sick> getSicks() {
        return sicks;
    }

    public void setSicks(Set<Sick> sicks) {
        this.sicks = sicks;
    }

    public String getPatientReport() {
        return patientReport;
    }

    public void setPatientReport(String patientReport) {
        this.patientReport = patientReport;
    }

    public User getPatient() {
        return patient;
    }

    public void setPatient(User patient) {
        this.patient = patient;
    }

}
