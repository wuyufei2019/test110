package com.cczu.sys.system.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @ClassName: VersionEntity
 * @Description: 项目版本更新表
 * @author jason
 * @date 2019年4月1日
 *
 */
@Entity
@Table(name = "t_version")
@DynamicUpdate @DynamicInsert
public class VersionEntity implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long id;//编号

	@Setter
	@Getter
	@Column(name = "name",columnDefinition="varchar(100)")
	private String name;//新版本名称

	@Setter
	@Getter
	@Column(name = "message",columnDefinition="varchar(1000)")
	private String message;//版本更新内容介绍

	@Setter
	@Getter
	@Column(name = "updatetime",columnDefinition="varchar(100)")
	private String updatetime;//更新日期


}
