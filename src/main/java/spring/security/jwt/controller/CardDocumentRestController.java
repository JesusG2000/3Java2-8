package spring.security.jwt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.security.jwt.bean.CardDocument;
import spring.security.jwt.controller.dto.CardDocumentRequest;
import spring.security.jwt.controller.dto.IdRequest;
import spring.security.jwt.service.impl.CardDocumentServiceImpl;

@RestController
public class CardDocumentRestController {
    @Autowired
    private CardDocumentServiceImpl cardDocumentService;


    @PostMapping("/patient/createCardDocument")
    public ResponseEntity<?> create(@RequestBody CardDocumentRequest cardDocumentRequest){
        cardDocumentService.create(new CardDocument(
                cardDocumentRequest.getCard(),
                cardDocumentRequest.getDoctorDocument()
        ));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/patient/isCardDocumentExist")
    public ResponseEntity<?> isExist(@RequestBody IdRequest idRequest){
        if(cardDocumentService.existsByCardId(idRequest.getId())){
            return new ResponseEntity<>(HttpStatus.FOUND);
        }else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
    @DeleteMapping("/patient/deleteCardDocumentByCardId")
    public ResponseEntity<?> deleteByCardIdPatient(@RequestBody IdRequest idRequest){
        cardDocumentService.deleteByCardId(idRequest.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @DeleteMapping("/doctor/deleteCardDocumentByCardId")
    public ResponseEntity<?> deleteByCardIdDoctor(@RequestBody IdRequest idRequest){
        cardDocumentService.deleteByCardId(idRequest.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
