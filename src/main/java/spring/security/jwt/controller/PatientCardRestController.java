package spring.security.jwt.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.bean.PatientCard;
import spring.security.jwt.bean.dto.Sick;
import spring.security.jwt.controller.dto.AlmostFullPatientCardRequest;
import spring.security.jwt.controller.dto.FullPatientCardRequest;
import spring.security.jwt.controller.dto.IdRequest;
import spring.security.jwt.controller.dto.PatientCardRequest;
import spring.security.jwt.exception.ControllerException;
import spring.security.jwt.exception.ServiceException;
import spring.security.jwt.service.impl.DoctorDocumentServiceImpl;
import spring.security.jwt.service.impl.PatientCardServiceImpl;
import spring.security.jwt.service.impl.UserServiceImpl;

import java.util.Collections;

@RestController
public class PatientCardRestController {
    @Autowired
    private UserServiceImpl userService;
    @Autowired
    private PatientCardServiceImpl patientCardService;

    @Autowired
    private DoctorDocumentServiceImpl doctorDocumentService;

    private static final Logger logger = Logger.getLogger(PatientCardRestController.class);



    @PostMapping("patient/createPatientCard")
    public ResponseEntity<?> create(@RequestBody PatientCardRequest patientCardRequest) throws ControllerException {
        try {
            patientCardService.savePatientCard(new PatientCard(
                    patientCardRequest.getName(),
                    patientCardRequest.getSurname(),
                    patientCardRequest.getFathername(),
                    patientCardRequest.getPatientReport(),
                    userService.findById(patientCardRequest.getUserId()),
                    Collections.singleton(Sick.valueOf(patientCardRequest.getSick()))
            ));
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error create patient card");

            throw new ControllerException("create", e);
        }
    }

    @PostMapping("/patient/isPatientCardExist")
    public ResponseEntity<?> isCardExist(@RequestBody IdRequest idRequest) throws ControllerException {
        try {
            if (patientCardService.existsByPatientId(idRequest.getId())) {
                return new ResponseEntity<>(HttpStatus.FOUND);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (ServiceException e) {
            logger.error("error is exist patient card");

            throw new ControllerException("isCardExist", e);
        }
    }

    @DeleteMapping("/patient/deletePatientCard")
    public ResponseEntity<?> delete(@RequestBody IdRequest idRequest) throws ControllerException {
        try {
            PatientCard patientCard = patientCardService.getByPatient_Id(idRequest.getId());
            patientCardService.deleteById(patientCard.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error delete patient card");

            throw new ControllerException("delete", e);
        }
    }

    @GetMapping("/patient/getPatientCard/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") Long id) throws ControllerException {
        try {
            PatientCard patientCard = patientCardService.getByPatient_Id(id);
            return new ResponseEntity<>(patientCard, HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error get  patient card");

            throw new ControllerException("get", e);
        }
    }

    @PutMapping("/patient/updatePatientCard")
    public ResponseEntity<?> update(@RequestBody AlmostFullPatientCardRequest patientCardRequest) throws ControllerException {
        try {
            patientCardService.setPatientCardById(
                    patientCardRequest.getId(),
                    patientCardRequest.getName(),
                    patientCardRequest.getSurname(),
                    patientCardRequest.getFathername(),
                    patientCardRequest.getPatientReport()
            );
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error update patient card");

            throw new ControllerException("update", e);
        }
    }

    @PutMapping("/doctor/updateFullPatientCard")
    public ResponseEntity<?> update(@RequestBody FullPatientCardRequest patientCardRequest) throws ControllerException {
        try {
            patientCardService.setPatientCardById(
                    patientCardRequest.getId(),
                    patientCardRequest.getName(),
                    patientCardRequest.getSurname(),
                    patientCardRequest.getFathername(),
                    patientCardRequest.getRecommendation(),
                    patientCardRequest.getPatientReport()
            );
        return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error update full patient card");

            throw new ControllerException("update", e);
        }
    }

    @GetMapping("/patient/getAllDoctors")
    public ResponseEntity<?> getAllDoctors() throws ControllerException {
        try {
            return new ResponseEntity<>(doctorDocumentService.getAll(), HttpStatus.OK);
        } catch (ServiceException e) {
            logger.error("error get all doctors");

            throw new ControllerException("getAllDoctors", e);
        }
    }
}
