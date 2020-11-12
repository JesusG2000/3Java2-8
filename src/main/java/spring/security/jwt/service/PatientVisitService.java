package spring.security.jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.PatientCard;
import spring.security.jwt.bean.PatientVisit;

import java.util.List;

@Service
public interface PatientVisitService {
    PatientVisit create(PatientVisit patientVisit);
    @Transactional
    void deleteAllByPatientCardId(long patientCard_id);
    List<PatientVisit> getAllByPatientCardId(long patientCard_id);
}
