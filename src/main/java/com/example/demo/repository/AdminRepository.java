package com.example.demo.repository;

import com.example.demo.model.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    public Admin findByLoginId(String loginId);
}
