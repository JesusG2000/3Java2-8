package spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.jwt.bean.PatientVisit;

import java.util.List;

@Repository
public interface PatientVisitRepository extends JpaRepository<PatientVisit , Long> {
    void deleteAllByPatientCardId(long patientCard_id);
    List<PatientVisit> getAllByPatientCardId(long patientCard_id);
}
