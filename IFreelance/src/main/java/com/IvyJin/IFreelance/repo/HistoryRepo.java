package com.IvyJin.IFreelance.repo;

import com.IvyJin.IFreelance.model.History;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryRepo extends JpaRepository<History, Integer> {
    @Query("SELECT p FROM History p WHERE p.freelancer = :username")
    List<History> findByFreelancer(String username);

    @Query("SELECT p FROM History p WHERE p.HR = :username")
    List<History> findByHR(String username);
}
