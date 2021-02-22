package com.antonio.apirestfulservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
 
import com.antonio.apirestfulservice.model.PersonalRoutine;
import com.antonio.apirestfulservice.exceptions.RecordNotFoundException;
import com.antonio.apirestfulservice.services.PersonalRoutineService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/personalroutine")
public class PersonalRoutineServiceController {
    
    @Autowired
    PersonalRoutineService service;
    
    @GetMapping("/{id_user}")
    public ResponseEntity<List<PersonalRoutine>> getAllPersonalRoutines(@PathVariable("id_user") Long id) {
        List<PersonalRoutine> list = service.getAllPersonalRoutines(id);
 
        return new ResponseEntity<List<PersonalRoutine>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{title}")
    public ResponseEntity<List<PersonalRoutine>> getRoutineByTitle(@PathVariable("title") String title){
    	List<PersonalRoutine> list = service.getRoutineByTitle(title);
 
        return new ResponseEntity<List<PersonalRoutine>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<PersonalRoutine> createItem(@Valid @RequestBody PersonalRoutine personalroutine){
    	PersonalRoutine created = service.createPersonalRoutine(personalroutine);
        return new ResponseEntity<PersonalRoutine>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<PersonalRoutine> updateItem(@Valid @RequestBody PersonalRoutine routine)
                                                    throws RecordNotFoundException {
    	PersonalRoutine updated = service.updatePersonalRoutine(routine);
        return new ResponseEntity<PersonalRoutine>(updated, new HttpHeaders(), HttpStatus.OK);
    }
       
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deletePersonalRoutine(id);
        return HttpStatus.ACCEPTED;
    }
    
}
