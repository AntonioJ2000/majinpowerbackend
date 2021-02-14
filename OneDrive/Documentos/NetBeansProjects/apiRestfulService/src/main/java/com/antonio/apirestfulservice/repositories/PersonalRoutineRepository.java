package com.antonio.apirestfulservice.repositories;

import com.antonio.apirestfulservice.model.PersonalRoutine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalRoutineRepository extends JpaRepository<PersonalRoutine, Long>{
    
    
    @Query(
    value="SELECT * FROM personal_routines WHERE personal_routines.id_user =?1", 
            nativeQuery=true)
    public List<PersonalRoutine> getAllPersonalRoutines(Long id_user);
    
    @Query(
    value="SELECT * FROM personal_routines WHERE personal_routines.title LIKE %=1%",
            nativeQuery = true)
    public List<PersonalRoutine> getByTitle(String title);
    
}
