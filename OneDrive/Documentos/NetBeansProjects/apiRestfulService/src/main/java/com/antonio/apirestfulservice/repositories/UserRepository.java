package com.antonio.apirestfulservice.repositories;

import com.antonio.apirestfulservice.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

    @Query(
    value="SELECT * FROM users WHERE users.login LIKE %?1%",
            nativeQuery=true)
    public List<User> getByName(String login);
    
    @Query(
    value="SELECT * FROM users WHERE users.login LIKE ?1 AND users.password LIKE ?2", 
            nativeQuery = true)
    public Optional<User> existUser(String login, String password);
    
}
