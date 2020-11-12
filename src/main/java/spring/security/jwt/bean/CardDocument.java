package spring.security.jwt.bean;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "card_document")
@Data
public class CardDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    private PatientCard card;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "document_id")
    private DoctorDocument doctorDocument;

    public CardDocument() {
    }

    public CardDocument(PatientCard card, DoctorDocument doctorDocument) {
        this.card = card;
        this.doctorDocument = doctorDocument;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public PatientCard getCard() {
        return card;
    }

    public void setCard(PatientCard card) {
        this.card = card;
    }

    public DoctorDocument getDoctorDocument() {
        return doctorDocument;
    }

    public void setDoctorDocument(DoctorDocument doctorDocument) {
        this.doctorDocument = doctorDocument;
    }
}
