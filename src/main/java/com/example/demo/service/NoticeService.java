package com.example.demo.service;

import com.example.demo.exception.ErrorCode;
import com.example.demo.exception.ServiceException;
import com.example.demo.model.entity.Notice;
import com.example.demo.repository.NoticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public Page<Notice> getList(Integer conferenceId, String subject, Pageable pageable){
        if (StringUtils.isEmpty(subject)) {
            return noticeRepository.findByConferenceIdAndEnabledYn(conferenceId, "Y", pageable);
        } else {
            return noticeRepository.findByConferenceIdAndSubjectLikeAndEnabledYn(conferenceId, "%"+subject+"%", "Y", pageable);
        }
    }

    @Transactional(readOnly = true)
    public Notice getById(Integer noticeId) {
        Optional<Notice> notice = noticeRepository.findById(noticeId);  // repository 에서는 Optional 로 받음
        if(notice.isPresent() == false){ // null이면 Exception
            throw new ServiceException(ErrorCode.BAD_PARAMETER);
        }
        return notice.get();
    }


}
