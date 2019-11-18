package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: EAD_AccidentWordEntity
 * @Description: 应急辅助决策_决策历史记录
 * @author jason
 *
 */
@Entity
@Table(name="ead_accidentword")
public class EAD_AccidentWordEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public EAD_AccidentWordEntity() {
	}

	public EAD_AccidentWordEntity(Long id) {
		this.ID=id;
	}
	
	@Column(name="ACCIDENTID", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private long accidentid;
	
	@Column(name="QYID", nullable=true, columnDefinition="bigint")
	@Setter
	@Getter
	private Long qyid;//企业
	
	@Column(name="QYNAME", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String qyname;//企业名称
	
	@Column(name="TYPE", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String type;//事故类型
	
	@Column(name="MATTER", nullable=true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String matter;//涉及危险化学品
	
	@Column(name="LNG", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String lng;//经度
	
	@Column(name="LAT", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String lat;//纬度
	
	@Column(name = "FILEURl", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String fileurl;//文件路径
	
	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//用户
	
	@Column(name = "ID2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String ID2;//行政区域
	
	
}
