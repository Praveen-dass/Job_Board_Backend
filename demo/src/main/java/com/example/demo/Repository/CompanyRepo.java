package com.example.demo.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.Company;


@Repository
public interface CompanyRepo extends JpaRepository<Company,Integer> {
    
}
