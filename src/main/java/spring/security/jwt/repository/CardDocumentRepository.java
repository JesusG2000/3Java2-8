package spring.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import spring.security.jwt.bean.CardDocument;
import spring.security.jwt.exception.RepositoryException;

import java.util.List;

@Repository
public interface CardDocumentRepository extends JpaRepository<CardDocument , Long> {
    boolean existsByCardId(long card_id) throws RepositoryException;
    void deleteByCardId(long card_id)throws RepositoryException;
    List<CardDocument> getAllByDoctorDocument_Id(long doctorDocument_id)throws RepositoryException;
    boolean existsByDoctorDocumentId(long doctorDocument_id)throws RepositoryException;
}
