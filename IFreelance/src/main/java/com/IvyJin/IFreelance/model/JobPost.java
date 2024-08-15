package com.IvyJin.IFreelance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String poster;
    private String title;
    private String job_description;
    private String category;
    private Boolean availability;
    @CreationTimestamp
    private LocalDateTime postTime;

}