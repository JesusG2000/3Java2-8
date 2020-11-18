package spring.security.jwt.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.bean.CardDocument;
import spring.security.jwt.bean.DoctorDocument;
import spring.security.jwt.bean.PatientCard;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.dto.DoctorSpec;
import spring.security.jwt.controller.dto.DoctorDocumentRequest;
import spring.security.jwt.controller.dto.IdRequest;
import spring.security.jwt.controller.dto.FullDoctorDocumentRequest;
import spring.security.jwt.exception.ControllerException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.repository.PatientCardRepository;
import spring.security.jwt.service.impl.CardDocumentServiceImpl;
import spring.security.jwt.service.impl.DoctorDocumentServiceImpl;
import spring.security.jwt.service.impl.UserServiceImpl;
import spring.security.jwt.validator.DoctorDocumentValidator;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@RestController
public class DoctorDocumentRestController {
    @Autowired
    private DoctorDocumentServiceImpl doctorDocumentService;
    @Autowired
    private CardDocumentServiceImpl cardDocumentService;
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PatientCardRepository patientCardRepository;

    @Autowired
    private DoctorDocumentValidator doctorDocumentValidator;



    private static final Logger logger = Logger.getLogger(DoctorDocumentRestController.class);

    @PostMapping("/doctor/createDoctorDocument")
    public ResponseEntity<?> create(@RequestBody @Validated DoctorDocumentRequest doctorDocumentRequest, BindingResult bindingResult) throws ControllerException {

        try {
            if (!bindingResult.hasErrors()) {
                User user = userService.findById(doctorDocumentRequest.getUserId());
                DoctorSpec spec = DoctorSpec.valueOf(doctorDocumentRequest.getDoctorSpec());
                DoctorDocument doc = doctorDocumentService.saveDoctorDocument(new DoctorDocument(
                        doctorDocumentRequest.getName(),
                        doctorDocumentRequest.getSurname(),
                        doctorDocumentRequest.getFathername(),
                        user,
                        Collections.singleton(spec)
                ));
                doctorDocumentValidator.validate(doc, bindingResult);
                if (!bindingResult.hasErrors()) {
                    return new ResponseEntity<>(HttpStatus.OK);
                }
                else{
                    logger.debug("not correct params");
                    return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
                }
            } else {
                logger.debug("not correct params");
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (ServiceException e) {
            logger.error("error create doctor document");

            throw new ControllerException("create", e);
        }
    }


    @PostMapping("/doctor/isExistDoctorDocument")
    public ResponseEntity<?> isExist(@RequestBody IdRequest idRequest) throws ControllerException {
        try {
            if (doctorDocumentService.existsByDoctorId(idRequest.getId())) {
                return new ResponseEntity<>(HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (ServiceException e) {
            logger.error("error is exist doctor document");

            throw new ControllerException("isExist", e);
        }
    }

    @DeleteMapping("/doctor/deleteDoctorDocument")
    public ResponseEntity<?> delete(@RequestBody IdRequest idRequest) throws ControllerException {
        try {
            DoctorDocument doctorDocument = doctorDocumentService.getByDoctor_Id(idRequest.getId());

            if (!cardDocumentService.existsByDoctorDocumentId(doctorDocument.getId())) {
                doctorDocumentService.deleteById(doctorDocument.getId());
                return new ResponseEntity<>(HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.FOUND);
            }
        } catch (ServiceException e) {
            logger.error("error delete doctor document");

            throw new ControllerException("delete", e);
        }
    }

    @GetMapping("/doctor/getDoctorDocument/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") Long id) throws ControllerException {
        try {
            DoctorDocument doctorDocument = doctorDocumentService.getByDoctor_Id(id);
            return new ResponseEntity<>(doctorDocument, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error get doctor document");

            throw new ControllerException("get", e);
        }

    }

    @GetMapping("/doctor/getAllSubPatients/{id}")
    public ResponseEntity<?> getAllSubPatients(@PathVariable(name = "id") Long id) throws ControllerException {
        try {
            DoctorDocument doctorDocument = doctorDocumentService.getByDoctor_Id(id);

            List<CardDocument> cardDocumentList = cardDocumentService.getAllByDoctorDocument_Id(doctorDocument.getId());
            List<PatientCard> patientCardList = new LinkedList<>();
            for (CardDocument cardDocument : cardDocumentList) {
                patientCardList.add(cardDocument.getCard());
            }
            return new ResponseEntity<>(patientCardList, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error get all subscribed patient");

            throw new ControllerException("getAllSubPatients", e);
        }
    }

    @PutMapping("/doctor/updateDoctorDocument")
    public ResponseEntity<?> update(@RequestBody FullDoctorDocumentRequest fullDoctorDocumentRequest) throws ControllerException {
        try {
            doctorDocumentService.setDoctorDocumentById(
                    fullDoctorDocumentRequest.getId(),
                    fullDoctorDocumentRequest.getName(),
                    fullDoctorDocumentRequest.getSurname(),
                    fullDoctorDocumentRequest.getFathername()
            );
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error update doctor document");

            throw new ControllerException("get", e);
        }
    }
}
