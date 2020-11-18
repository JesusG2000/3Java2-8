package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.DoctorDocument;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.dto.DoctorSpec;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.DoctorDocumentRepository;
import spring.security.jwt.service.DoctorDocumentService;

import java.util.List;
import java.util.Set;

@Service
public class DoctorDocumentServiceImpl implements DoctorDocumentService {
    @Autowired
    private DoctorDocumentRepository doctorDocumentRepository;

    @Override
    public DoctorDocument saveDoctorDocument(DoctorDocument doctorDocument) throws ServiceException {
        return doctorDocumentRepository.save(doctorDocument);
    }

    @Override
    public List<DoctorDocument> getAll()throws ServiceException {
        return doctorDocumentRepository.findAll();
    }

    @Override
    public boolean existsByDoctorId(Long doctorId) throws ServiceException {
        try {
            return doctorDocumentRepository.existsByDoctor_Id(doctorId);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteById(long id)throws ServiceException {
        doctorDocumentRepository.deleteById(id);
    }

    @Override
    public DoctorDocument getByDoctor_Id(Long doctor_id) throws ServiceException {
        try {
            return doctorDocumentRepository.getByDoctor_Id(doctor_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setDoctorDocumentById(Long id, String name, String surname, String fathername) throws ServiceException {
        try {
            doctorDocumentRepository.setDoctorDocumentById(id, name, surname, fathername);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }


}
