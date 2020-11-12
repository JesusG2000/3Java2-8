package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.CardDocument;
import spring.security.jwt.repository.CardDocumentRepository;
import spring.security.jwt.service.CardDocumentService;

import java.util.List;

@Service
public class CardDocumentServiceImpl implements CardDocumentService {
    @Autowired
    private CardDocumentRepository cardDocumentRepository;
    @Override
    public CardDocument create(CardDocument cardDocument) {
        return cardDocumentRepository.save(cardDocument);
    }

    @Override
    public void deleteByCardId(long cardId) {
        cardDocumentRepository.deleteByCardId(cardId);
    }

    @Override
    public List<CardDocument> getAllByDoctorDocument_Id(long doctorDocument_id) {
        return cardDocumentRepository.getAllByDoctorDocument_Id(doctorDocument_id);
    }

    @Override
    public boolean existsByDoctorDocumentId(long doctorDocument_id) {
        return cardDocumentRepository.existsByDoctorDocumentId(doctorDocument_id);
    }

    @Override
    public boolean existsByCardId(long card_id) {
        return cardDocumentRepository.existsByCardId(card_id);
    }
}
