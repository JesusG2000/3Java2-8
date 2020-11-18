package spring.security.jwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.DoctorDocument;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.dto.DoctorSpec;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.DoctorDocumentRepository;

import java.util.List;
import java.util.Set;

@Service
public interface DoctorDocumentService {

    DoctorDocument saveDoctorDocument(DoctorDocument doctorDocument)throws ServiceException, RepositoryException;

    List<DoctorDocument> getAll()throws ServiceException, RepositoryException;

    boolean existsByDoctorId(Long doctorId) throws ServiceException, RepositoryException;

    void deleteById(long id)throws ServiceException, RepositoryException;

    DoctorDocument getByDoctor_Id(Long doctor_id) throws ServiceException, RepositoryException;

    @Transactional
    void setDoctorDocumentById(Long id, String name, String surname, String fathername) throws ServiceException, RepositoryException;

}
