package com.cczu.model.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_OccupIllexamEntity
 * @Description: 企业基本信息-职业卫生_职业病体检
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_occupillexam")
public class BIS_OccupIllexamEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1279955916255916146L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true,columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M1;//体检日期
	
	@Column(name = "M2", nullable = true, length = 100)
	@Setter
	@Getter
	private String M2;//体检医院

	@Column(name = "M3", nullable = true)
	@Setter
	@Getter	
	private Integer M3;//体检人数
	
	@Column(name = "M4", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M4;//体检结论
	
	@Column(name = "M5", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M5;//附件

	@Column(name = "M6", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M6;//体检类型

	@Column(name = "M7", nullable = true, length = 500)
	@Setter
	@Getter	
	private String M7;//备注
}
