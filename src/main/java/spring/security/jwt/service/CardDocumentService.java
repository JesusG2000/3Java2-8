package spring.security.jwt.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.jwt.bean.CardDocument;
import spring.security.jwt.exception.RepositoryException;
import spring.security.jwt.exception.ServiceException;

import java.util.List;

@Service
public interface CardDocumentService {
    CardDocument create(CardDocument cardDocument) throws RepositoryException, ServiceException;

    boolean existsByCardId(long card_id) throws ServiceException, RepositoryException;
    @Transactional
    void deleteByCardId(long cardId) throws ServiceException, RepositoryException;
    List<CardDocument> getAllByDoctorDocument_Id(long doctorDocument_id) throws ServiceException, RepositoryException;
    boolean existsByDoctorDocumentId(long doctorDocument_id) throws ServiceException, RepositoryException;
}
