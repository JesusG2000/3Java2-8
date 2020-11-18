package spring.security.jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.PatientCard;
import spring.security.jwt.bean.PatientVisit;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;

import java.util.List;

@Service
public interface PatientVisitService {
    PatientVisit create(PatientVisit patientVisit)throws ServiceException, RepositoryException;
    @Transactional
    void deleteAllByPatientCardId(long patientCard_id)throws ServiceException, RepositoryException;
    List<PatientVisit> getAllByPatientCardId(long patientCard_id)throws ServiceException, RepositoryException;
}
