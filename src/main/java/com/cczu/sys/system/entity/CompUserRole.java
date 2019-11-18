package com.cczu.sys.system.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @description 企业内部用户和角色之间的关系表
 * @author jason
 * @date 2017年12月26日
 */
@Entity
@Table(name = "t_comp_user_role")
public class CompUserRole implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	@Getter
	@Setter
	private Integer id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID", nullable = false)
	@Getter
	@Setter
	private User user;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ROLE_ID", nullable = false)
	@Getter
	@Setter
	private CompRole compRole;

	// Constructors

	/** default constructor */
	public CompUserRole() {
	}

	/** full constructor */
	public CompUserRole(User user, CompRole compRole) {
		this.user = user;
		this.compRole = compRole;
	}

}