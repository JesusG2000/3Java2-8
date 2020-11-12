package spring.security.jwt.controller;

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

    @PostMapping("patient/createPatientCard")
    public ResponseEntity<?> create(@RequestBody PatientCardRequest patientCardRequest) {
        patientCardService.savePatientCard(new PatientCard(
                patientCardRequest.getName(),
                patientCardRequest.getSurname(),
                patientCardRequest.getFathername(),
                patientCardRequest.getPatientReport(),
                userService.findById(patientCardRequest.getUserId()),
                Collections.singleton(Sick.valueOf(patientCardRequest.getSick()))
        ));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/patient/isPatientCardExist")
    public ResponseEntity<?> isCardExist(@RequestBody IdRequest idRequest) {
        if (patientCardService.existsByPatientId(idRequest.getId())) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/patient/deletePatientCard")
    public ResponseEntity<?> delete(@RequestBody IdRequest idRequest) {
        PatientCard patientCard = patientCardService.getByPatient_Id(idRequest.getId());
        patientCardService.deleteById(patientCard.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/patient/getPatientCard/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") Long id) {
        PatientCard patientCard = patientCardService.getByPatient_Id(id);
        return new ResponseEntity<>(patientCard, HttpStatus.OK);
    }

    @PutMapping("/patient/updatePatientCard")
    public ResponseEntity<?> update(@RequestBody AlmostFullPatientCardRequest patientCardRequest) {
        patientCardService.setPatientCardById(
                patientCardRequest.getId(),
                patientCardRequest.getName(),
                patientCardRequest.getSurname(),
                patientCardRequest.getFathername(),
                patientCardRequest.getPatientReport()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/doctor/updateFullPatientCard")
    public ResponseEntity<?> update(@RequestBody FullPatientCardRequest patientCardRequest) {
        patientCardService.setPatientCardById(
                patientCardRequest.getId(),
                patientCardRequest.getName(),
                patientCardRequest.getSurname(),
                patientCardRequest.getFathername(),
                patientCardRequest.getRecommendation(),
                patientCardRequest.getPatientReport()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/patient/getAllDoctors")
    public ResponseEntity<?> getAllDoctors() {
        return new ResponseEntity<>(doctorDocumentService.getAll(), HttpStatus.OK);
    }
}
