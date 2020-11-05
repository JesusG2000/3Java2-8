package spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import spring.security.jwt.bean.DoctorDocument;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.dto.DoctorSpec;

import java.util.Set;

@Repository
public interface DoctorDocumentRepository extends JpaRepository<DoctorDocument, Long> {
    boolean existsByDoctor_Id(Long doctor_Id);

    DoctorDocument getByDoctor_Id(Long doctor_id);

    @Modifying
    @Query("update DoctorDocument d set d.name =:name, d.surname=:surname, d.fathername =:fathername  where d.id =:id ")
    void setDoctorDocumentById(
            @Param("id") Long id,
            @Param("name") String name,
            @Param("surname") String surname,
            @Param("fathername") String fathername);
}
