package com.antonio.apirestfulservice.repositories;

import com.antonio.apirestfulservice.model.Routine;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository extends JpaRepository<Routine, Long>{
 
    @Query(
    value="SELECT * FROM routines WHERE routines.title LIKE %?1%",
            nativeQuery=true)
    public List<Routine> getByTitle(String title);
}
