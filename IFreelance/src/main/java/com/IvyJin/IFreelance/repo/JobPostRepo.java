package com.IvyJin.IFreelance.repo;

import com.IvyJin.IFreelance.model.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobPostRepo extends JpaRepository<JobPost, Integer> {

    @Query("SELECT p FROM JobPost p WHERE p.poster = :username AND p.availability = true")
    List<JobPost> findByUsername(String username);

    @Query("SELECT p FROM JobPost p WHERE p.category = :category AND p.availability = true")
    List<JobPost> findByCategory(String category);

}
