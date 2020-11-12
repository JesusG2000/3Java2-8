package spring.security.jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.CardDocument;

import java.util.List;

@Service
public interface CardDocumentService {
    CardDocument create(CardDocument cardDocument);

    boolean existsByCardId(long card_id);
    @Transactional
    void deleteByCardId(long cardId);
    List<CardDocument> getAllByDoctorDocument_Id(long doctorDocument_id);
    boolean existsByDoctorDocumentId(long doctorDocument_id);
}
