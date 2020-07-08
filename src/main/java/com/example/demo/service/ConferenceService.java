package com.example.demo.service;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ServiceException;
import com.example.demo.model.entity.Conference;
import com.example.demo.repository.ConferenceRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Slf4j
public class ConferenceService {
    @Autowired
    private ConferenceRepository conferRepository;

    @Transactional(readOnly = true)
    public Conference getById(Integer conferenceId) {
        Optional<Conference> confer = conferRepository.findById(conferenceId);
        if(confer.isPresent() == false) {
            throw new ServiceException(ErrorCode.BAD_PARAMETER);
        }
        return confer.get();
    }
}
