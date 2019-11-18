package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: TMESK_AccidentEntity
 * @Description: 安全专家知识库_事故案例
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="tmesk_aqbskgl")
public class TMESK_AqbskglEntity extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6167553517295513085L;

	@Column(name = "ID1", nullable = true, length = 8, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//创建者

	@Column(name = "M1", nullable = true, length = 50, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M1;//类别

	@Column(name = "M2", nullable = true, length = 500, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M2;//名称

	@Column(name = "M3", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M3;//图片地址

}
