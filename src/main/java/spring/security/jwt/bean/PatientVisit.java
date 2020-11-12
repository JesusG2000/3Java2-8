package spring.security.jwt.bean;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "patient_visit")
@Data
public class PatientVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Date date;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patientCard_id")
    private PatientCard patientCard;

    public PatientVisit() {
    }

    public PatientVisit(Date date, PatientCard patientCard) {
        this.date = date;
        this.patientCard = patientCard;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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
