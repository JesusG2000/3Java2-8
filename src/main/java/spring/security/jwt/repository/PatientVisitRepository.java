package spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.jwt.bean.PatientVisit;
import spring.security.jwt.exception.RepositoryException;

import java.util.List;

@Repository
public interface PatientVisitRepository extends JpaRepository<PatientVisit , Long> {
    void deleteAllByPatientCardId(long patientCard_id)throws RepositoryException;
    List<PatientVisit> getAllByPatientCardId(long patientCard_id)throws RepositoryException;
}
