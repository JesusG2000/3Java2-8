package spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import spring.security.jwt.bean.PatientCard;

public interface PatientCardRepository extends JpaRepository<PatientCard , Long> {
    boolean existsByPatient_Id(Long patient_id);
    PatientCard getByPatient_Id(Long patient_id);
    @Modifying
    @Query("update PatientCard p set p.name =:name, p.surname=:surname, p.fathername =:fathername ,p.patientReport=:patientReport where p.id =:id ")
    void setPatientCardById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("fathername") String fathername,
            @Param("patientReport") String patientReport);
    @Modifying
    @Query("update PatientCard p set p.name =:name, p.surname=:surname, p.fathername =:fathername,p.recommendation=:recommendation ,p.patientReport=:patientReport where p.id =:id ")
    void setPatientCardById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("fathername") String fathername,
            @Param("recommendation") String recommendation,
            @Param("patientReport") String patientReport);
}
