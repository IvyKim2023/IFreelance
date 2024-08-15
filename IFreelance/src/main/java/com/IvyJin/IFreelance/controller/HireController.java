package com.IvyJin.IFreelance.controller;

import com.IvyJin.IFreelance.model.History;
import com.IvyJin.IFreelance.model.JobPost;
import com.IvyJin.IFreelance.service.JWTService;
import com.IvyJin.IFreelance.service.FreelanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/hire")
public class HireController {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private FreelanceService service;

    @GetMapping("/jobposts")
    public ResponseEntity<List<JobPost>> getAllPosts(){
        return new ResponseEntity<>(service.getAllPosts(), HttpStatus.OK);
    }

    @GetMapping("/jobposts/{category}")
    public ResponseEntity<List<JobPost>> getCategoryPosts(@PathVariable String category){
        return new ResponseEntity<>(service.getPostByCategory(category), HttpStatus.OK);
    }

    @GetMapping("/jobpost/{id}")
    public ResponseEntity<JobPost> getPost(@PathVariable int id){
        JobPost post = service.getPostById(id);
        if(post != null)
            return new ResponseEntity<>(post, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<History> hire(@RequestHeader("Authorization") String authHeader,
                                              @PathVariable int id){
        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);
        History hire = service.hire(username, service.getPostById(id));
        return new ResponseEntity<>(hire, HttpStatus.OK);
    }

    @GetMapping("/hirehistory")
    public ResponseEntity<List<History>> hireHistory(@RequestHeader("Authorization") String authHeader){
        String token = authHeader.substring(7);
        String username = jwtService.extractUserName(token);
        List<History> posts = service.getHireHistory(username);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

}
