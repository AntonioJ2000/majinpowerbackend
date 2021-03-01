package com.antonio.apirestfulservice.services;

import com.antonio.apirestfulservice.exceptions.RecordNotFoundException;
import com.antonio.apirestfulservice.repositories.PersonalRoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.antonio.apirestfulservice.model.PersonalRoutine;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PersonalRoutineService {
    
    @Autowired
    PersonalRoutineRepository repository;
    
    public List<PersonalRoutine> getAllPersonalRoutines(Long id)
    {
        List<PersonalRoutine> personalRoutines = repository.getAllPersonalRoutines(id);
        if(personalRoutines.size() > 0){
            return personalRoutines;
        }else{
            return new ArrayList<PersonalRoutine>();
        }     
    }
    
    public PersonalRoutine createPersonalRoutine(PersonalRoutine entity)
    {
        entity = repository.save(entity);
        
        return entity;
    }
    
    public PersonalRoutine updatePersonalRoutine(PersonalRoutine entity) throws RecordNotFoundException
    {
        if(entity.getId() != 0)
        {
            Optional<PersonalRoutine> personalRoutine = repository.findById(entity.getId());
            
            
            if(personalRoutine.isPresent())
            {
                PersonalRoutine newPersonalRoutine = personalRoutine.get();
                
                newPersonalRoutine.setTitle(entity.getTitle());
                newPersonalRoutine.setDescription(entity.getDescription());
                newPersonalRoutine.setDuration(entity.getDuration());
                newPersonalRoutine.setDifficulty(entity.getDifficulty());
                newPersonalRoutine.setTimesDone(entity.getTimesDone());
                newPersonalRoutine.setUser(entity.getUser());
                
                newPersonalRoutine = repository.save(newPersonalRoutine);
                
                return newPersonalRoutine;
            }else{
                throw new RecordNotFoundException("Personal Routine not found", entity.getId());
            }
            
        }else{
            throw new RecordNotFoundException("No ID of Personal Routine given", 0l);
        }
             
    }
    
    public void deletePersonalRoutine(Long id) throws RecordNotFoundException
    {
        Optional<PersonalRoutine> routine = repository.findById(id);
        
        if(routine.isPresent()){
            repository.deleteById(id);
        }else
            throw new RecordNotFoundException("No Personal Routine record exist for the given ID", id);
    }
    
    public List<PersonalRoutine> getRoutineByTitle(String title) 
    {
        List<PersonalRoutine> personalRoutineList = repository.getByTitle(title);
        if(personalRoutineList.size() > 0){
            return personalRoutineList;
        }else{
            return new ArrayList<PersonalRoutine>();
        }              
    }
    
}
