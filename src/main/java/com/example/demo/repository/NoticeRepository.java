package com.example.demo.repository;

import com.example.demo.model.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoticeRepository extends JpaRepository<Notice, Integer> {
    public Page<Notice> findByConferenceIdAndEnabledYn(Integer conferenceId, String enabledYn, Pageable pageable);

    public Page<Notice> findByConferenceIdAndSubjectLikeAndEnabledYn(Integer conferenceId, String subject, String enabledYn, Pageable pageable);
}
