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
 * @ClassName: DW_WorkApprovalEntity
 * @Description: 企业-危险作业审批
 * @author jason
 * @date 2017年7月11日
 *
 */
@Entity
@Table(name="dw_workapproval")
public class DW_WorkApprovalEntity extends BaseEntity {


	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7953885314461733114L;
	
	@Column(name = "ID1", nullable = true,columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业ID
	
	@Column(name = "M1", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//危险作业方式
	
	@Column(name = "M2", nullable = true,columnDefinition="bigint")
	@Setter
	@Getter
	private Long M2;//危险作业级别

	@Column(name = "M3", nullable = true,columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//作业地点
	
	@Column(name = "M4", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M4;//作业负责人
	
	@Column(name = "M5", nullable = true,columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M5;//作业人员
	
	@Column(name = "M6", nullable = true,columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M6;//作业内容
	
	@Column(name = "M7", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M7;//时间起
	
	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M8;//时间止
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(150)")
	@Setter
	@Getter
	private String M9;//危害因素
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(150)")
	@Setter
	@Getter
	private String M10;//其他危害因素
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(150)")
	@Setter
	@Getter
	private String M11;//可能导致的事故类型	
	
	@Column(name = "M12", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M12;//附件(作业票)
	
	@Column(name = "M13", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M13;//附件(作业方案)
	
	@Column(name = "M14", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M14;//附件(现场照片)
	
	@Column(name = "M15", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M15;//附件(应急方案)
	
	@Column(name = "M16", nullable = true, length = 2000)
	@Setter
	@Getter	
	private String M16;//附件(人员证件)
	
	@Column(name = "M17", nullable = true,columnDefinition="int")
	@Setter
	@Getter
	private Integer M17;//作业队伍
	
	@Column(name = "M18", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M18;//第三方服务公司
	
	@Column(name = "M19", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M19;//第三方公司作业负责人
	
	@Column(name = "M20", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M20;//作业类型（1.特种作业/2.一般作业）
}
