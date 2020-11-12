package spring.security.jwt.controller.dto;

import spring.security.jwt.bean.DoctorDocument;
import spring.security.jwt.bean.PatientCard;

public class CardDocumentRequest {
    private PatientCard card;

    private DoctorDocument doctorDocument;

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
