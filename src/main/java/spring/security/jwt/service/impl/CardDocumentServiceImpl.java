package spring.security.jwt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.security.jwt.bean.CardDocument;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.CardDocumentRepository;
import spring.security.jwt.service.CardDocumentService;

import javax.sql.rowset.serial.SerialException;
import java.util.List;

@Service
public class CardDocumentServiceImpl implements CardDocumentService {
    @Autowired
    private CardDocumentRepository cardDocumentRepository;
    @Override
    public CardDocument create(CardDocument cardDocument) throws  ServiceException {
            return cardDocumentRepository.save(cardDocument);
    }

    @Override
    public void deleteByCardId(long cardId) throws ServiceException {
        try {
            cardDocumentRepository.deleteByCardId(cardId);
        }catch (RepositoryException e){
            throw new ServiceException(e);
        }

    }

    @Override
    public List<CardDocument> getAllByDoctorDocument_Id(long doctorDocument_id) throws ServiceException {
        try {
            return cardDocumentRepository.getAllByDoctorDocument_Id(doctorDocument_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean existsByDoctorDocumentId(long doctorDocument_id) throws ServiceException {
        try {
            return cardDocumentRepository.existsByDoctorDocumentId(doctorDocument_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean existsByCardId(long card_id) throws ServiceException {
        try {
            return cardDocumentRepository.existsByCardId(card_id);
        } catch (RepositoryException e) {
            throw new ServiceException(e);
        }
    }
}
