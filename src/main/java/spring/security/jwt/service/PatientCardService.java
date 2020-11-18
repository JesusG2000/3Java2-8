package spring.security.jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.PatientCard;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;

@Service
public interface PatientCardService {
    PatientCard savePatientCard(PatientCard patientCard)throws ServiceException, RepositoryException;

    boolean existsByPatientId(Long patientId)throws ServiceException, RepositoryException;

    void deleteById(long id)throws ServiceException, RepositoryException;

    PatientCard getByPatient_Id(Long patient_id)throws ServiceException, RepositoryException;

    @Transactional
    void setPatientCardById(
            Long id,
            String name,
            String surname,
            String fathername,
            String patientReport)throws ServiceException, RepositoryException;

    @Transactional
    void setPatientCardById(
            Long id,
            String name,
            String surname,
            String fathername,
            String recommendation,
            String patientReport)throws ServiceException;
}
