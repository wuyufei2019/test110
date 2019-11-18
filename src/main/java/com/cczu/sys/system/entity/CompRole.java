package com.cczu.sys.system.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @description 企业内部角色entity
 * @author jason
 * @date 2017年12月26日
 */
@Entity
@Table(name = "t_comp_role")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
@DynamicUpdate @DynamicInsert
public class CompRole implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false,columnDefinition="int")
	@Setter
	@Getter
	private Integer id;
	
	@Column(name = "name", nullable = false, length = 20,columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String name;
	
	@Column(name = "role_code", nullable = false, length = 20,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String roleCode;
	
	@Column(name = "description", columnDefinition="text")
	@Setter
	@Getter
	private String description;
	
	@Column(name = "sort",columnDefinition="int")
	@Setter
	@Getter
	private Short sort;
	
	@Column(name = "del_flag", length = 1)
	@Setter
	@Getter
	private String delFlag;
	
	@Column(name = "qyid", nullable = false,columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "compRole")
	@Setter
	@Getter
	private Set<CompUserRole> compUserRoles = new HashSet<CompUserRole>(0);

	// Constructors
	/** default constructor */
	public CompRole() {
	}
	
	public CompRole(Integer id) {
		this.id=id;
	}

	/** minimal constructor */
	public CompRole(String name, String roleCode) {
		this.name = name;
		this.roleCode = roleCode;
	}

	public CompRole(Integer id, String name, String roleCode,
			String description, Short sort, String delFlag, Long qyid,
			Set<CompUserRole> compUserRoles) {
		super();
		this.id = id;
		this.name = name;
		this.roleCode = roleCode;
		this.description = description;
		this.sort = sort;
		this.delFlag = delFlag;
		this.qyid = qyid;
		this.compUserRoles = compUserRoles;
	}

	 
}