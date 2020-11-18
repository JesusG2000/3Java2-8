package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.PatientVisit;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.PatientVisitRepository;
import spring.security.jwt.service.PatientVisitService;

import java.util.List;

@Service
public class PatientVisitServiceImpl implements PatientVisitService {
    @Autowired
    private PatientVisitRepository patientVisitRepository;
    @Override
    public PatientVisit create(PatientVisit patientVisit)throws ServiceException {
        return patientVisitRepository.save(patientVisit);
    }

    @Override
    public void deleteAllByPatientCardId(long patientCard_id)throws ServiceException {
        try {
            patientVisitRepository.deleteAllByPatientCardId(patientCard_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<PatientVisit> getAllByPatientCardId(long patientCard_id) throws ServiceException{
        try {
            return patientVisitRepository.getAllByPatientCardId(patientCard_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }


}
