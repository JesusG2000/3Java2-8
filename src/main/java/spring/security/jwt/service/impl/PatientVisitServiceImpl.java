package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.PatientVisit;
import spring.security.jwt.repository.PatientVisitRepository;
import spring.security.jwt.service.PatientVisitService;

import java.util.List;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {
    @Autowired
    private PatientVisitRepository patientVisitRepository;
    @Override
    public PatientVisit create(PatientVisit patientVisit) {
        return patientVisitRepository.save(patientVisit);
    }

    @Override
    public void deleteAllByPatientCardId(long patientCard_id) {
        patientVisitRepository.deleteAllByPatientCardId(patientCard_id);
    }

    @Override
    public List<PatientVisit> getAllByPatientCardId(long patientCard_id) {
        return patientVisitRepository.getAllByPatientCardId(patientCard_id);
    }


}
