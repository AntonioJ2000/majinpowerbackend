package com.antonio.apirestfulservice.services;

import com.antonio.apirestfulservice.exceptions.RecordNotFoundException;
import com.antonio.apirestfulservice.model.User;
import com.antonio.apirestfulservice.repositories.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    
    @Autowired
    UserRepository repository;
    
    public List<User> getAllUsers()
    {
        List<User> userList = repository.findAll();
        if(userList.size() > 0){
            return userList;
        }else{
            return new ArrayList<User>();
        }
    }
    
    public List<User> getFighterzTierlist() {
        List<User> userList = repository.getFighterzTierlist();
        if(userList.size() > 0){
            return userList;
        }else{
            return new ArrayList<User>();
        }   
    }
    
    public User getUserById(Long id) throws RecordNotFoundException
    {
        Optional<User> user = repository.findById(id);
        if(user.isPresent()){
            return user.get();
            
        }else{
            throw new RecordNotFoundException("No user record exists for given id",id);
        }
    }
    
    public User createUser(User entity)
    {
        entity = repository.save(entity);
        
        return entity;
    }
    
    public User updateUser(User entity) throws RecordNotFoundException
    {
        if(entity.getId() != 0)
        {
            Optional<User> user = repository.findById(entity.getId());
            
            if(user.isPresent())
            {
                User newUser = user.get();
                
                newUser.setLoginName(entity.getLoginName());
                newUser.setPassword(entity.getPassword());
                newUser.setZpower(entity.getZpower());
                newUser.setPersonalRoutines(entity.getPersonalRoutines());
                newUser.setImage(entity.getImage());
                
                newUser = repository.save(newUser);
                
                return newUser;
            }else{
                throw new RecordNotFoundException("User not found", entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No ID of User given", 0l);
        }
    }
    
    public void deleteUserByID(Long id) throws RecordNotFoundException
    {
        Optional<User> user = repository.findById(id);
        
        if(user.isPresent()){
            repository.deleteById(id);
        }else
            throw new RecordNotFoundException("No User record exist for the given ID", id);
    }

    public List<User> getUsersByName(String login) 
    {
        List<User> userList = repository.getByName(login);
        if(userList.size() > 0){
            return userList;
        }else{
            return new ArrayList<User>();
        }
                
    }
    
    public User existUser(String login, String password)       
    {
         User usuarioDF = new User();
         usuarioDF.setId(-1l);
        try{
            Optional<User> user = repository.existUser(login, password);
            if(user!=null){
                return user.get();
            }
        }catch(Exception e){
            throw new RecordNotFoundException("No User record exist for the given ID", -1l);
        }
        return usuarioDF;
    }

    
  
}
