package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.PatientCard;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.PatientCardRepository;
import spring.security.jwt.service.PatientCardService;

@Service
public class PatientCardServiceImpl implements PatientCardService {
    @Autowired
    private PatientCardRepository patientCardRepository;

    @Override
    public PatientCard savePatientCard(PatientCard patientCard)throws ServiceException {
        return patientCardRepository.save(patientCard);
    }

    @Override
    public boolean existsByPatientId(Long patientId)throws ServiceException {
        try {
            return patientCardRepository.existsByPatient_Id(patientId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(long id)throws ServiceException {
        patientCardRepository.deleteById(id);
    }

    @Override
    public PatientCard getByPatient_Id(Long patient_id)throws ServiceException {
        try {
            return patientCardRepository.getByPatient_Id(patient_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setPatientCardById(Long id, String name, String surname, String fathername, String patientReport)throws ServiceException {
        try {
            patientCardRepository.setPatientCardById(id, name, surname, fathername, patientReport);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setPatientCardById(Long id, String name, String surname, String fathername, String recommendation, String patientReport)throws ServiceException {
        try {
            patientCardRepository.setPatientCardById(id, name, surname, fathername, recommendation, patientReport);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
