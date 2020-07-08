package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_conference")
@Data
public class Conference {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="conference_id")
    private Integer id;

    @Column(name="project_id")
    private String projectId;

    @Column(name="conference_name")
    private String name;

    @Column(name="conference_code")
    private String code;

    @Column(name="conference_status")
    private Short status;

    @Column(name="start_date")
    private String startDate;

    @Column(name="end_date")
    private String endDate;

    @Column(name="app_open_date")
    private String openDate;

    @Column(name="app_close_date")
    private String closeDate;

    @Column(name="office_name")
    private String officeName;

    @Column(name="office_tel")
    private String officeTel;

    @Column(name="push_key")
    private String pushKey;

    @Column(name="insert_time", updatable=false)
    private Date insertTime;

    @Column(name="update_time")
    private Date updateTime;

    @Column(name="survey")
    private String survey;

    @Column(name="survey_link")
    private String surveyLink;
}
