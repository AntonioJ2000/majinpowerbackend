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
 
import com.antonio.apirestfulservice.model.User;
import com.antonio.apirestfulservice.exceptions.RecordNotFoundException;
import com.antonio.apirestfulservice.services.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/user")
public class UserServiceController {
    
    @Autowired
    UserService service;
 
    @GetMapping
    public ResponseEntity<List<User>> getAllItems() {
        List<User> list = service.getAllUsers();
 
        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/topfighterz")
    public ResponseEntity<List<User>> getAllFighterzList(){
        List<User> list = service.getFighterzTierlist();
        
        return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
    } 
    
    @GetMapping("/{id}")
    public ResponseEntity<User> getItemById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
    	User entity = service.getUserById(id);
 
        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{login}")
    public ResponseEntity<List<User>> getUsersByName(@PathVariable("login") String login){
    	List<User> userList = service.getUsersByName(login);
 
        return new ResponseEntity<List<User>>(userList, new HttpHeaders(), HttpStatus.OK);
    }
    
    @GetMapping("/exists/{login}_{password}")
    public ResponseEntity<User> existUser(@PathVariable("login") String login, @PathVariable("password") String password){
        User entity = service.existUser(login, password);
        
        return new ResponseEntity<User>(entity, new HttpHeaders(), HttpStatus.OK);
    }
 
   @PostMapping
    public ResponseEntity<User> createItem(@Valid @RequestBody User myUser){
    	User created = service.createUser(myUser);
        return new ResponseEntity<User>(created, new HttpHeaders(), HttpStatus.OK);
    }
    
    @PutMapping
    public ResponseEntity<User> updateItem(@Valid @RequestBody User myUser)
                                                    throws RecordNotFoundException {
    	User updated = service.updateUser(myUser);
        return new ResponseEntity<User>(updated, new HttpHeaders(), HttpStatus.OK);
    }
 
    @DeleteMapping("/{id}")
    public HttpStatus deleteItemById(@PathVariable("id") Long id)
                                                    throws RecordNotFoundException {
        service.deleteUserByID(id);
        return HttpStatus.ACCEPTED;
    }
    
}
