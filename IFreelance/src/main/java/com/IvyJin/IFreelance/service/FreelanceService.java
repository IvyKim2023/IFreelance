package com.IvyJin.IFreelance.service;

import com.IvyJin.IFreelance.model.History;
import com.IvyJin.IFreelance.model.JobPost;
import com.IvyJin.IFreelance.repo.HistoryRepo;
import com.IvyJin.IFreelance.repo.JobPostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class FreelanceService {

    @Autowired
    private JobPostRepo jobpostRepo;

    @Autowired
    private HistoryRepo historyRepo;

    public List<JobPost> getAllPosts() {
        return jobpostRepo.findAll();
    }

    public JobPost getPostById(int id) {
        return jobpostRepo.findById(id).orElse(null);
    }

    public List<JobPost> getPostByCategory(String category) {
        return jobpostRepo.findByCategory(category);
    }

    public JobPost post(JobPost post) throws IOException {
        return jobpostRepo.save(post);
    }

    public JobPost updatePost(int id, JobPost post) throws IOException {
        deletePost(id);
        return jobpostRepo.save(post);
    }

    public void deletePost(int id) {
        jobpostRepo.deleteById(id);
    }

    public History hire(String username, JobPost post) {
        post.setAvailability(false);
        jobpostRepo.save(post);
        History hire = new History();
        hire.setHR(username);
        hire.setFreelancer(post.getPoster());
        hire.setPost(post);
        return historyRepo.save(hire);
    }

    public List<JobPost> getMyCurrentPosts(String username) {
        return jobpostRepo.findByUsername(username);
    }

    public List<History> getOfferHistory(String username) {return historyRepo.findByFreelancer(username);}

    public List<History> getHireHistory(String username) {
        return historyRepo.findByHR(username);
    }
}
