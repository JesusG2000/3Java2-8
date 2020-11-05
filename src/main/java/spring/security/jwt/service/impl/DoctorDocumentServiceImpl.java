package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.DoctorDocument;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.dto.DoctorSpec;
import spring.security.jwt.repository.DoctorDocumentRepository;
import spring.security.jwt.service.DoctorDocumentService;

import java.util.Set;

@Service
public class DoctorDocumentServiceImpl implements DoctorDocumentService {
    @Autowired
    private DoctorDocumentRepository doctorDocumentRepository;

    @Override
    public DoctorDocument saveDoctorDocument(DoctorDocument doctorDocument) {
        return doctorDocumentRepository.save(doctorDocument);
    }

    @Override
    public boolean existsByDoctorId(Long doctorId) {
        return doctorDocumentRepository.existsByDoctor_Id(doctorId);
    }

    @Override
    public void deleteById(long id) {
        doctorDocumentRepository.deleteById(id);
    }

    @Override
    public DoctorDocument getByDoctor_Id(Long doctor_id) {
        return doctorDocumentRepository.getByDoctor_Id(doctor_id);
    }

    @Override
    public void setDoctorDocumentById(Long id, String name, String surname, String fathername) {
         doctorDocumentRepository.setDoctorDocumentById(id, name, surname, fathername);
    }


}
