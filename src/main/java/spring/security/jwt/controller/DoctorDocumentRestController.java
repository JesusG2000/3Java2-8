package spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.bean.DoctorDocument;
import spring.security.jwt.bean.User;
import spring.security.jwt.bean.dto.DoctorSpec;
import spring.security.jwt.controller.dto.DoctorDocumentRequest;
import spring.security.jwt.controller.dto.DoctorIdRequest;
import spring.security.jwt.controller.dto.FullDoctorDocumentRequest;
import spring.security.jwt.service.impl.DoctorDocumentServiceImpl;
import spring.security.jwt.service.impl.UserServiceImpl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@RestController
public class DoctorDocumentRestController {
    @Autowired
    private DoctorDocumentServiceImpl doctorDocumentService;
    @Autowired
    private UserServiceImpl userService;

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
    public ResponseEntity<?> isExist(@RequestBody DoctorIdRequest doctorIdRequest) {
        if (doctorDocumentService.existsByDoctorId(doctorIdRequest.getId())) {
            return new ResponseEntity<>(HttpStatus.FOUND);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

    @DeleteMapping("/doctor/deleteDoctorDocument")
    public ResponseEntity<?> delete(@RequestBody DoctorIdRequest doctorIdRequest) {
        DoctorDocument doctorDocument  = doctorDocumentService.getByDoctor_Id(doctorIdRequest.getId());
        doctorDocumentService.deleteById(doctorDocument.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/doctor/getDoctorDocument/{id}")
    public ResponseEntity<?> get(@PathVariable(name = "id") Long id) {
        DoctorDocument doctorDocument  = doctorDocumentService.getByDoctor_Id(id);
        return new ResponseEntity<>(doctorDocument,HttpStatus.OK);
    }
    @PutMapping("/doctor/updateDoctorDocument")
    public ResponseEntity<?> get(@RequestBody FullDoctorDocumentRequest fullDoctorDocumentRequest) {
        System.out.println(fullDoctorDocumentRequest);

         doctorDocumentService.setDoctorDocumentById(
                fullDoctorDocumentRequest.getId(),
                fullDoctorDocumentRequest.getName(),
                fullDoctorDocumentRequest.getSurname(),
                fullDoctorDocumentRequest.getFathername()
        );
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
