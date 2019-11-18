package com.cczu.sys.system.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;


/**
 * @ClassName: CompUserDep
 * @Description: 用户部门权限范围表
 * @author jason
 *
 */
@Entity
@Table(name="t_comp_user_dep")
public class CompUserDep implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7440973518403243606L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//用户ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID2;//部门ID
	
}
