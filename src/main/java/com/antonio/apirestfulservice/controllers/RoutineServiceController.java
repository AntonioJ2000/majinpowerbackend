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
 
import com.antonio.apirestfulservice.model.Routine;
import com.antonio.apirestfulservice.exceptions.RecordNotFoundException;
import com.antonio.apirestfulservice.services.RoutineService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/routine")
public class RoutineServiceController {
    
    @Autowired
    RoutineService service;
    
    @GetMapping
    public ResponseEntity<List<Routine>> getAllRoutines() {
        List<Routine> list = service.getAllRoutines();
 
        return new ResponseEntity<List<Routine>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Routine> getRoutineById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	Routine entity = service.getRoutineById(id);
 
        return new ResponseEntity<Routine>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{title}")
    public ResponseEntity<List<Routine>> getRoutineByTitle(@PathVariable("title") String title){
    	List<Routine> routineList = service.getRoutineByTitle(title);
 
        return new ResponseEntity<List<Routine>>(routineList, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Routine> createItem(@Valid @RequestBody Routine routine){
    	Routine created = service.createRoutine(routine);
        return new ResponseEntity<Routine>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<Routine> updateItem(@Valid @RequestBody Routine routine)
                                                    throws RecordNotFoundException {
    	Routine updated = service.updateRoutine(routine);
        return new ResponseEntity<Routine>(updated, new HttpHeaders(), HttpStatus.OK);
    }
    
     @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteUserByID(id);
        return HttpStatus.ACCEPTED;
    }
    
 
}
