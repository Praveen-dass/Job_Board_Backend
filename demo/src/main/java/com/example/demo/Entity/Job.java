package com.example.demo.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.w3c.dom.Text;

@Data
@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String jobName;
    private String companyName;
    private String location;
    private Double salary;
    private String shiftTime;
    private String description;
    private String jobType;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String briefDescription;
}
