package com.example.demo.service;

import com.example.demo.model.entity.Admin;
import com.example.demo.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Transactional(readOnly = true)
    public Admin getByLoginId(String loginId) {
        return adminRepository.findByLoginId(loginId);
    }
}
