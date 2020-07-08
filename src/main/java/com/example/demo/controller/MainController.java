package com.example.demo.controller;

import com.example.demo.def.ConferenceStatus;
import com.example.demo.def.RoleLevel;
import com.example.demo.model.LoginSession;
import com.example.demo.model.entity.Admin;
import com.example.demo.model.entity.Conference;
import com.example.demo.service.AdminService;
import com.example.demo.service.ConferenceService;
import com.example.demo.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Web controller for mvc
 *
 * */
@Controller
@Slf4j
public class MainController {
    @Autowired
    private AdminService adminService;

    @Autowired
    private ConferenceService conferenceService;

    /**
     * 메인 페이지 요청 처리
     * */
    @RequestMapping("/")
    public String main(LoginSession session, Model model) {
        model.addAttribute("name", session.getName());

        log.debug("### {}", session);
        
        return "main";
    }


    /**
     * 로그인 폼으로 이동
     * */
    @GetMapping("/login")
    public String loginForm(){
       return "login";
    }

    /**
     * 로그인 요청 처리
     * */
    @PostMapping("/login")
    public String login(String loginId, String loginPwd, HttpSession session, Model model) {
        log.debug("@login: id={}, pwd={}", loginId, loginPwd);

        // id를 통해 admin 정보를 가져옴
        Admin admin = adminService.getByLoginId(loginId);

        //로그인 정보 일치하지 않는 경우
        if(admin == null || admin.getLoginPwd().equals(loginPwd) == false) {
            model.addAttribute("error", "로그인 정보가 일치하지 않습니다.");
            return "login";
        }

        //로그인을 막은 경우
        if("N".equals(admin.getAllowYn())) {
            model.addAttribute("error", "로그인이 제한되었습니다.");
            return "login";
        }

        Conference conference = conferenceService.getById(admin.getConferenceId()); // conference 정보를 가져온다.
        ConferenceStatus status = ConferenceStatus.from(conference.getStatus());    // conference 의 상태를 가져온다.

        // 로그인 세션 저장
        LoginSession loginSession = LoginSession.from(admin);
        session.setAttribute(LoginSession.KEY, loginSession);
        log.debug("Session created: {}", session);
        return "redirect:/";
    }



    /**
     *  로그아웃 요청 처리
     * */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        log.debug("@logout: {}", session);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
