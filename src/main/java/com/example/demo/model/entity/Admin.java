package com.example.demo.model.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="tb_admin")
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="admin_id")
	private Integer id;
	
	@Column(name="admin_name")
	private String name;
	
	@Column(name="login_id")
	private String loginId;
	
	@Column(name="login_pwd")
	private String loginPwd;
	
	@Column(name="login_allow_yn")
	private String allowYn;
	
	@Column(name="role_level")
	private Short roleLevel;
	
	@Column(name="conference_id", updatable=false)
	private Integer conferenceId;

	private String email;
	
	private String memo;
	
	@Column(name="login_allow_fromdate")
	private String allowFromdate;
	
	@Column(name="login_allow_todate")
	private String allowTodate;
	
	@Column(name="insert_time", updatable=false)
	private Date insertTime;

	@Column(name="update_time")
	private Date updateTime;
	
	@Column(name="enabled_yn")
	private String enabledYn;
}
