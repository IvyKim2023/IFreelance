package com.IvyJin.IFreelance.repo;

import com.IvyJin.IFreelance.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<AppUser, Integer> {
    @Query("SELECT p FROM AppUser p WHERE p.username = :username")
    AppUser findByUsername(String username);

    @Query("SELECT COUNT(p) > 0 FROM AppUser p WHERE p.username = :username")
    boolean existsByUsername(String username);
}
