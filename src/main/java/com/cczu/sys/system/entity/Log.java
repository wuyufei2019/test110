package com.cczu.sys.system.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


/**
 * 日志entity
 * @author jason
 * @date 2017年5月31日
 */
@Entity
@Table(name = "t_log")
@DynamicUpdate @DynamicInsert
public class Log implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@Getter
	@Setter
	private Integer id;
	
	@Column(name = "userid", nullable = false)
	@Getter
	@Setter
	private Integer userid;
	
	@Column(name = "username", nullable = false, length = 100)
	@Getter
	@Setter
	private String username;
	
	@Column(name = "createdate", nullable = false, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp createDate;
	
	@Column(name = "type", nullable = false, length = 100)
	@Getter
	@Setter
	private String type;
	
	@Column(name = "classinfo", nullable = false, length = 100)
	@Getter
	@Setter
	private String classinfo;
	
	@Column(name = "method", nullable = false, length = 100)
	@Getter
	@Setter
	private String method;
	
	@Column(name = "browser", nullable = false, length = 100)
	@Getter
	@Setter
	private String browser;
	
	@Column(name = "ip", nullable = false, length = 100)
	@Getter
	@Setter
	private String ip;
	
	@Column(name = "executetime", nullable = false)
	@Getter
	@Setter
	private Integer executeTime;
	
	@Column(name = "description", nullable = false, length = 100)
	@Getter
	@Setter
	private String description;
	
	@Column(name = "requesturi", nullable = false, length = 100)
	@Getter
	@Setter
	private String requestUri;
	
	@Column(name = "requestparam", nullable = false, length = 100)
	@Getter
	@Setter
	private String requestParam;

	

}