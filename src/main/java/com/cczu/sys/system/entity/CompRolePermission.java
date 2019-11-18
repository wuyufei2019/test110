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


/**
 * @description 企业内部角色与权限信息关系表
 * @author jason
 * @date 2017年12月26日
 */
@Entity
@Table(name = "t_comp_role_permission")
public class CompRolePermission implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	private Integer id;
	private Permission permission;
	private CompRole compRole;

	// Constructors

	/** default constructor */
	public CompRolePermission() {
	}

	/** full constructor */
	public CompRolePermission(Permission permission, CompRole compRole) {
		this.permission = permission;
		this.compRole = compRole;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "ID", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PERMISSION_ID")
	public Permission getPermission() {
		return this.permission;
	}

	public void setPermission(Permission permission) {
		this.permission = permission;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "COMPROLE_ID")
	public CompRole getCompRole() {
		return this.compRole;
	}

	public void setCompRole(CompRole compRole) {
		this.compRole = compRole;
	}

}