package com.cczu.sys.system.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户自定义页面记录
 * @author jason
 * @date 2017年5月31日
 */
@Entity
@Table(name = "user_define")
public class User_Define implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号

	@Column(name = "userid", columnDefinition="bigint")
	@Setter
	@Getter
	private Long userid;//用户id

	@Column(name = "menuid",columnDefinition="varchar(500)")
	@Setter	
	@Getter
	private String menuid;//用户选择要显示的菜单（逗号隔开）
}