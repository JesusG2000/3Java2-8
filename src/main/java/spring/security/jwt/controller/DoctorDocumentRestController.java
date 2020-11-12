package spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.bean.CardDocument;
import spring.security.jwt.bean.DoctorDocument;
import spring.security.jwt.bean.PatientCard;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.dto.DoctorSpec;
import spring.security.jwt.controller.dto.DoctorDocumentRequest;
import spring.security.jwt.controller.dto.IdRequest;
import spring.security.jwt.controller.dto.FullDoctorDocumentRequest;
import spring.security.jwt.repository.PatientCardRepository;
import spring.security.jwt.service.impl.CardDocumentServiceImpl;
import spring.security.jwt.service.impl.DoctorDocumentServiceImpl;
import spring.security.jwt.service.impl.UserServiceImpl;

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

    @PostMapping("/doctor/createDoctorDocument")
    public ResponseEntity<?> create(@RequestBody DoctorDocumentRequest doctorDocumentRequest) {
        User user = userService.findById(doctorDocumentRequest.getUserId());
        DoctorSpec spec = DoctorSpec.valueOf(doctorDocumentRequest.getDoctorSpec());
        doctorDocumentService.saveDoctorDocument(new DoctorDocument(
                doctorDocumentRequest.getName(),
                doctorDocumentRequest.getSurname(),
                doctorDocumentRequest.getFathername(),
                user,
                Collections.singleton(spec)
        ));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/doctor/isExistDoctorDocument")
    public ResponseEntity<?> isExist(@RequestBody IdRequest idRequest) {
        if (doctorDocumentService.existsByDoctorId(idRequest.getId())) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/doctor/deleteDoctorDocument")
    public ResponseEntity<?> delete(@RequestBody IdRequest idRequest) {
        DoctorDocument doctorDocument  = doctorDocumentService.getByDoctor_Id(idRequest.getId());
        if(!cardDocumentService.existsByDoctorDocumentId(doctorDocument.getId())) {
            doctorDocumentService.deleteById(doctorDocument.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.FOUND);
        }
    }
    @GetMapping("/doctor/getDoctorDocument/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") Long id) {
        DoctorDocument doctorDocument  = doctorDocumentService.getByDoctor_Id(id);
        return new ResponseEntity<>(doctorDocument,HttpStatus.OK);
    }

    @GetMapping("/doctor/getAllSubPatients/{id}")
    public ResponseEntity<?> getAllSubPatients(@PathVariable(name = "id") Long id) {
        DoctorDocument doctorDocument  = doctorDocumentService.getByDoctor_Id(id);
        List<CardDocument> cardDocumentList = cardDocumentService.getAllByDoctorDocument_Id(doctorDocument.getId());
        List<PatientCard> patientCardList = new LinkedList<>();
        for (CardDocument cardDocument : cardDocumentList) {
            patientCardList.add(cardDocument.getCard());
        }
        return new ResponseEntity<>(patientCardList,HttpStatus.OK);
    }
    @PutMapping("/doctor/updateDoctorDocument")
    public ResponseEntity<?> get(@RequestBody FullDoctorDocumentRequest fullDoctorDocumentRequest) {
         doctorDocumentService.setDoctorDocumentById(
                fullDoctorDocumentRequest.getId(),
                fullDoctorDocumentRequest.getName(),
                fullDoctorDocumentRequest.getSurname(),
                fullDoctorDocumentRequest.getFathername()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
