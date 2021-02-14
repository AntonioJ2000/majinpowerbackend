package com.antonio.apirestfulservice.services;

import com.antonio.apirestfulservice.exceptions.RecordNotFoundException;
import com.antonio.apirestfulservice.repositories.RoutineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.antonio.apirestfulservice.model.Routine;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RoutineService {
    
    @Autowired
    RoutineRepository repository;
    
    public List<Routine> getAllRoutines()
    {
        List<Routine> userList = repository.findAll();
        if(userList.size() > 0){
            return userList;
        }else{
            return new ArrayList<Routine>();
        }      
    }
 
    public Routine getRoutineById(Long id) throws RecordNotFoundException
    {
        Optional<Routine> routine = repository.findById(id);
        if(routine.isPresent()){
            return routine.get();
        }else{
            throw new RecordNotFoundException("No routine exists for given id", id);
        }
        
    }
    
    public Routine createRoutine(Routine entity)
    {
        entity = repository.save(entity);
        
        return entity;
    }
    
    public Routine updateRoutine(Routine entity) throws RecordNotFoundException
    {
        if(entity.getId() != 0)
        {
            Optional<Routine> routine = repository.findById(entity.getId());
            
            if(routine.isPresent())
            {
                Routine newRoutine = routine.get();
                
                newRoutine.setTitle(entity.getTitle());
                newRoutine.setDescription(entity.getDescription());
                newRoutine.setDuration(entity.getDuration());
                newRoutine.setDifficulty(entity.getDifficulty());
                newRoutine.setTimesDone(entity.getTimesDone());
                
                newRoutine = repository.save(newRoutine);
                
                return newRoutine;
            }else{
                throw new RecordNotFoundException("Routine not found", entity.getId());
            }
        }else{
            throw new RecordNotFoundException("No ID of Routine given", 0l);
        }      
    }
    
    public void deleteUserByID(Long id) throws RecordNotFoundException
    {
        Optional<Routine> routine = repository.findById(id);
        
        if(routine.isPresent()){
            repository.deleteById(id);
        }else
            throw new RecordNotFoundException("No Routine record exist for the given ID", id);
    }
    
    public List<Routine> getRoutineByTitle(String title) 
    {
        List<Routine> routineList = repository.getByTitle(title);
        if(routineList.size() > 0){
            return routineList;
        }else{
            return new ArrayList<Routine>();
        }              
    }
    
}
