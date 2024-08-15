package com.IvyJin.IFreelance.controller;

import com.IvyJin.IFreelance.model.History;
import com.IvyJin.IFreelance.model.JobPost;
import com.IvyJin.IFreelance.service.JWTService;
import com.IvyJin.IFreelance.service.FreelanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/freelance")
public class FreelanceController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private FreelanceService service;

    @PostMapping("/post")
    public ResponseEntity<?> createJobPost(@RequestBody JobPost post){
        try {
            JobPost post1 = service.post(post);
            return new ResponseEntity<>(post1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/post/update/{id}")
    public ResponseEntity<String> updatePost(@PathVariable int id,
                                             @RequestBody JobPost post){
        JobPost product1 = null;
        try {
            product1 = service.updatePost(id, post);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if(product1 != null)
            return new ResponseEntity<>("Updated successfully", HttpStatus.OK);
        else
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/post/delete/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id){
        JobPost post = service.getPostById(id);
        if(post != null) {
            service.deletePost(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }
        else
            return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/post/mycurrent")
    public ResponseEntity<List<JobPost>> currentPosts(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);
        List<JobPost> posts = service.getMyCurrentPosts(username);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/offerhistory")
    public ResponseEntity<List<History>> offerHistory(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);
        List<History> offers = service.getOfferHistory(username);
        return new ResponseEntity<>(offers, HttpStatus.OK);
    }
}
