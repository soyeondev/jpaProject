package com.example.demo.model;

//import com.example.demo.model.entity.Admin;
import com.example.demo.model.entity.Admin;
import lombok.Data;

import java.util.Date;

@Data
public class LoginSession {
    public static final String KEY = "sessionKey";

    private int id;
    private String name;
    private String loginId;
    private int roleLevel;
    private int conferenceId;
    private String email;
    private String allowFromdate;
    private String allowTodate;
    private Date insertTime;

    public static LoginSession from(Admin admin) {
        LoginSession session = new LoginSession();
        session.setId(admin.getId().intValue());
        session.setName(admin.getName());
        session.setLoginId(admin.getLoginId());
        session.setRoleLevel(admin.getRoleLevel().intValue());

        if (admin.getConferenceId() != null ){
            session.setConferenceId(admin.getConferenceId());
        }

        session.setEmail(admin.getEmail());
        session.setAllowFromdate(admin.getAllowFromdate());
        session.setAllowTodate(admin.getAllowTodate());
        session.setInsertTime(admin.getInsertTime());

        return session;
    }
}
