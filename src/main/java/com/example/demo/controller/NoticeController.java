package com.example.demo.controller;

import com.example.demo.model.LoginSession;
import com.example.demo.model.entity.Notice;
import com.example.demo.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    /**
     * 공지사항 list
     * @param pageable
     * @param session
     * @param model
     * @param request
     * */
    @RequestMapping("/notice/list")
    public String noticeList(@PageableDefault(size=10, sort = "insertTime", direction = Sort.Direction.DESC) Pageable pageable,
                             LoginSession session, Model model, HttpServletRequest request){
        log.debug("### {}", session);

        String searchVal = request.getParameter("searchVal");

//        Integer conferId = session.getConferenceId();
        Integer conferId = 1;

        Page<Notice> noticeList = noticeService.getList(conferId, searchVal, pageable);

        model.addAttribute("noticeList", noticeList);
        model.addAttribute("searchVal", searchVal);

        return "/notice/list";
    }

    /**
     * 공지사항 수정 페이지 이동
     * @param noticeId
     * @param session
     * @param model
     * @param request
     * */
    @RequestMapping("/notice/{noticeId}")
    public String noticeDetail(@PathVariable("noticeId") Integer noticeId, LoginSession session, Model model, HttpServletRequest request) {
        log.debug("### {}", session);

        Notice notice = noticeService.getById(noticeId);
        model.addAttribute("notice", notice);

        return "/notice/update";
    }
}
