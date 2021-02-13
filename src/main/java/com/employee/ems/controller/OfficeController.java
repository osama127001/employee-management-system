package com.employee.ems.controller;

import com.employee.ems.model.ErrorMessage;
import com.employee.ems.model.Office;
import com.employee.ems.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("api/v1/office")
public class OfficeController {

    private final OfficeService officeService;

    @Autowired
    public OfficeController(OfficeService officeService) {
        this.officeService = officeService;
    }


    @GetMapping("/")
    public ResponseEntity<?> getOffices() {
        Optional<List<Office>> offices = Optional.ofNullable(officeService.getOffices());
        if (offices.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(offices.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Could not found Offices!"));

    }


    @GetMapping("/{office_id}")
    public ResponseEntity<?> getOffice(@PathVariable("office_id") String id) {
        Optional<Office> optionalOffice = Optional.ofNullable(officeService.getOffice(id));
        if (optionalOffice.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalOffice.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Could not found Office with id: !" + id));
    }


    @PostMapping("/")
    public ResponseEntity<?> insertOffice(@RequestBody Office office) {
        Optional<Serializable> optionalSavedOffice = officeService.saveOffice(office);
        if (optionalSavedOffice.isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(optionalSavedOffice.get());
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage("Cannot save the object"));
    }


    @PutMapping("/{office_id}")
    public ResponseEntity<?> updateOffice(@RequestBody Office office, @PathVariable("office_id") String id) {
        if (officeService.updateOffice(id, office)) {
            return ResponseEntity
                    .status(HttpStatus.OK).build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Cannot Update Entity"));
    }


    @DeleteMapping("/{office_id}")
    public ResponseEntity<?> deleteOffice(@PathVariable("office_id") String id) {
        if (officeService.deleteOffice(id)) {
            return ResponseEntity
                    .status(HttpStatus.OK).build();
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(new ErrorMessage("Cannot Delete Entity"));
    }

}
