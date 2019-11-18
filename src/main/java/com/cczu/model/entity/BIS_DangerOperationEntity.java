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
 * @ClassName: BIS_DangerOperationEntity
 * @Description: 企业基本信息-危险作业
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_dangeroperation")
public class BIS_DangerOperationEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 5139871150763096635L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//作业名称

	@Column(name = "M2", columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp M2;//时间起

	@Column(name = "M3", columnDefinition="datetime" )
	@Setter
	@Getter	
	private Timestamp M3;//时间止

	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M4;//作业频次
	
	@Column(name = "M5", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M5;//备注

}
