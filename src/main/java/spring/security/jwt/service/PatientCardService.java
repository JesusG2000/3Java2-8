package spring.security.jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.PatientCard;

@Service
public interface PatientCardService {
    PatientCard savePatientCard(PatientCard patientCard);

    boolean existsByPatientId(Long patientId);

    void deleteById(long id);

    PatientCard getByPatient_Id(Long patient_id);

    @Transactional
    void setPatientCardById(
            Long id,
            String name,
            String surname,
            String fathername,
            String patientReport);

    @Transactional
    void setPatientCardById(
            Long id,
            String name,
            String surname,
            String fathername,
            String recommendation,
            String patientReport);
}
