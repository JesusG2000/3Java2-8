package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.PatientCard;
import spring.security.jwt.repository.PatientCardRepository;
import spring.security.jwt.service.PatientCardService;

@Service
public class PatientCardServiceImpl implements PatientCardService {
    @Autowired
    private PatientCardRepository patientCardRepository;

    @Override
    public PatientCard savePatientCard(PatientCard patientCard) {
        return patientCardRepository.save(patientCard);
    }

    @Override
    public boolean existsByPatientId(Long patientId) {
        return patientCardRepository.existsByPatient_Id(patientId);
    }

    @Override
    public void deleteById(long id) {
        patientCardRepository.deleteById(id);
    }

    @Override
    public PatientCard getByPatient_Id(Long patient_id) {
        return patientCardRepository.getByPatient_Id(patient_id);
    }

    @Override
    public void setPatientCardById(Long id, String name, String surname, String fathername, String patientReport) {
        patientCardRepository.setPatientCardById(id, name, surname, fathername, patientReport);
    }

    @Override
    public void setPatientCardById(Long id, String name, String surname, String fathername, String recommendation, String patientReport) {
        patientCardRepository.setPatientCardById(id, name, surname, fathername, recommendation, patientReport);
    }
}
