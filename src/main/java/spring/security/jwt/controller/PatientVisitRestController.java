package spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.bean.PatientVisit;
import spring.security.jwt.controller.dto.IdRequest;
import spring.security.jwt.controller.dto.PatientVisitRequest;
import spring.security.jwt.service.impl.PatientVisitServiceImpl;

@RestController
public class PatientVisitRestController {
    @Autowired
    private PatientVisitServiceImpl patientVisitService;

    @PostMapping("patient/addPatientVisit")
    public ResponseEntity<?> add(@RequestBody PatientVisitRequest patientVisitRequest) {
        patientVisitService.create(new PatientVisit(
                patientVisitRequest.getDate(),
                patientVisitRequest.getPatientCard()
        ));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/patient/deleteAllPatientVisit")
    public ResponseEntity<?> deleteAll(@RequestBody IdRequest idRequest) {
        patientVisitService.deleteAllByPatientCardId(idRequest.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/patient/getAllPatientVisit/{id}")
    public ResponseEntity<?> getAll(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(patientVisitService.getAllByPatientCardId(id),HttpStatus.OK);
    }
}
