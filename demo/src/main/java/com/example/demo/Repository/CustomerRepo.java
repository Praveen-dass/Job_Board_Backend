package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Entity.CustomerDetail;





@Repository
public interface CustomerRepo extends JpaRepository<CustomerDetail,String> {
    
}