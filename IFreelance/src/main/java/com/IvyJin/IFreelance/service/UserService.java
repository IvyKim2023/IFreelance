package com.IvyJin.IFreelance.service;

import com.IvyJin.IFreelance.model.AppUser;
import com.IvyJin.IFreelance.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    private UserRepo repo;


    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public ResponseEntity<?> register(AppUser user) {
        if (repo.existsByUsername(user.getUsername())) {
            return new ResponseEntity<>("Username already exists, try another one.", HttpStatus.CONFLICT);
        }
        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public String verify(AppUser user) {
        Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());
        } else {
            return "fail";
        }
    }
}
