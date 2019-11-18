package com.cczu.model.mbgl.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: Target_SafetyAdvice
 * @Description: 目标管理-安全文化-建言献策
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="target_safetyadvice")
public class Target_SafetyAdvice extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2888187391651750192L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业id
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//用户id
	
	@Column(name = "name", nullable = false, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String name;//建言人姓名
	
	@Column(name = "theme", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String theme;//主题

	@Column(name = "content", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter	
	private String content;//信息内容

	@Column(name = "url", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String url;//文件路径
	
	@Column(name = "type", nullable = true, columnDefinition="varchar(8)")
	@Setter
	@Getter	
	private String type;//类别     隐患  ；措施 ；经验 ； 其他
	
}
