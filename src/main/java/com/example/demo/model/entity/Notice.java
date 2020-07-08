package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_notice")
@Data
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notice_id")
    private Integer id;

    @Column(name="conference_id", updatable = false)
    private Integer conferenceId;

    @Column(name="show_level")
    private Integer showLevel;

    @Column(name="main_yn")
    private String mainYn;

    @Column(name="show_yn")
    private String showYn;

    @Column(name="enabled_yn")
    private String enabledYn;

    @Column(name="subject")
    private String subject;

    @Column(name="contents")
    private String contents;

    @Column(name="admin_id", updatable=false)
    private Integer adminId;

    @Column(name="admin_name")
    private String adminName;

    @Column(name="insert_time", updatable=false)
    private Date insertTime;

    @Column(name="update_time")
    private Date updateTime;

}
