package com.example.demo.repository;

import com.example.demo.model.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConferenceRepository extends JpaRepository<Conference, Integer> {
}
