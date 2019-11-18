package com.cczu.model.ztzyaqgl.entity;


import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: AQGL_Lsydzy
 * @Description: 安全管理-临时用电作业
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="ztaqgl_lsydzy")
public class ZTAQGL_Lsydzy extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	//票证签发人填表
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M1;//作业证编号
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M2;//申请单位

	@Column(name = "M3", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M3;//申请人

	@Column(name = "M4", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M4;//作业时间起
	
	@Column(name = "M5", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M5;//作业时间止

	@Column(name = "M6", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M6;//作业地点

	@Column(name = "M7", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M7;//电源接入点

	@Column(name = "M8", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8;//工作电压

	@Column(name = "M9", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M9;//用电设备及功率

	@Column(name = "M10", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M10;//作业等级(内部用电，变配电所用电)
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M11;//作业人
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M12;//电工证号
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M13;//安全监护人

	@Column(name = "M14", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M14;//实施安全教育人
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M15;//危害辨识

	@Column(name = "M15_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M15_1;//其他危害辨识
	
	//作业单位签字
	@Column(name = "M16", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M16;//作业单位意见
	
	@Column(name = "M16_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M16_1;//作业单位签字（图片）
	
	@Column(name = "M16_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M16_2;//作业单位确认时间
	
	@Column(name = "M16_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M16_3;//添加人id

	//配送电部门签字
	@Column(name = "M17", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M17;//配送电部门意见
	
	@Column(name = "M17_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M17_1;//配送电部门签字（图片）
	
	@Column(name = "M17_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M17_2;//配送电部门确认时间
	
	@Column(name = "M17_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M17_3;//添加人id
	
	//公司内部审批
	@Column(name = "M18", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M18;//安技员意见
	
	@Column(name = "M18_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M18_1;//安技员签字（图片）
	
	@Column(name = "M18_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M18_2;//安技员确认时间
	
	@Column(name = "M18_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M18_3;//安技员id

	@Column(name = "M18_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M18_4;//审批结果（通过，不通过）
	
	@Column(name = "M19", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M19;//安全科长意见
	
	@Column(name = "M19_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M19_1;//安全科长签字（图片）

	@Column(name = "M19_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M19_2;//安全科长确认时间

	@Column(name = "M19_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M19_3;//安全科长id

	@Column(name = "M19_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M19_4;//审批结果（通过，不通过）

	@Column(name = "M20", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M20;//条线分管领导意见
	
	@Column(name = "M20_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M20_1;//条线分管领导签字（图片）

	@Column(name = "M20_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M20_2;//条线分管领导确认时间

	@Column(name = "M20_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M20_3;//条线分管领导id

	@Column(name = "M20_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M20_4;//审批结果（通过，不通过）
	
	
	@Column(name = "M21", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M21;//能源管控中心意见
	
	@Column(name = "M21_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M21_1;//能源管控中心签字（图片）

	@Column(name = "M21_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M21_2;//能源管控中心确认时间

	@Column(name = "M21_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M21_3;//能源管控中心id

	@Column(name = "M21_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M21_4;//审批结果（通过，不通过）
	
	
	//安全措施确认后作业，过程前及过程中分析
	@Column(name = "M22", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M22;//编制时间

	@Column(name = "M22_1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M22_1;//编制人

	@Column(name = "M22_2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M22_2;//编制确认负责人签字（图片）
	
	@Column(name = "M22_3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M22_3;//编制确认许可人签字（图片）

	@Column(name = "M22_4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M22_4;//安全交底（附件）
	
	@Column(name = "M22_5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M22_5;//施工方案（附件）

	@Column(name = "M22_6", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M22_6;//外来单位id（逗号隔开）

	@Column(name = "M22_7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M22_7;//编制确认时间
	
	//完工确认
	@Column(name = "M23", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M23;//作业单位完工时间	
	
	@Column(name = "M23_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M23_1;//作业单位完工签字（图片）
	
	@Column(name = "M23_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M23_2;//签字人id

	@Column(name = "M24", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M24;//分厂完工时间	
	
	@Column(name = "M24_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M24_1;//分厂完工签字（图片）

	@Column(name = "M24_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M24_2;//签字人id

	@Column(name = "M25", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M25;//能控中心完工时间	
	
	@Column(name = "M25_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M25_1;//能控中心完工签字（图片）

	@Column(name = "M25_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M25_2;//签字人id
	
	@Column(name = "zt", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String zt;//-2：作废 -1：撤回 0:待编制 1:待作业单位签字 2:待安技员签字 3:待安全科长  4:待条线分管领导  5：待能源管控中心签字 6：待确认措施  7：待作业单位完工签字  8：待分厂单位完工签字  9：待能控中心完工签字  10：完工 
}
