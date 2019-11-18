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
 * @ClassName: AQGL_FireWork
 * @Description: 安全管理-动火作业
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="ztaqgl_firework")
public class ZTAQGL_FireWork extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业id

	@Column(name = "ID2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID2;//分析人id
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M1;//作业证编号
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M2;//申请单位

	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M3;//申请人

	@Column(name = "M4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M4;//动火作业级别

	@Column(name = "M4_1", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M4_1;//特殊情况
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M5;//动火地点
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M6;//动火方式
	
	@Column(name = "M7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M7;//动火时间起
	
	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M8;//动火时间止
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M9;//动火作业负责人

	@Column(name = "M10", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M10;//动火人

	@Column(name = "M11", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M11;//监火人
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M12;// 涉及的其他特殊作业(1对多)
	
	@Column(name = "M12_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M12_1;// 其他特殊作业
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M13;//危害辨识

	@Column(name = "M13_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M13_1;//其他危害辨识
	
	//作业单位签字
	@Column(name = "M14", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M14;//作业单位意见
	
	@Column(name = "M14_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M14_1;//作业单位签字（图片）	
	
	@Column(name = "M14_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M14_2;//作业单位签字时间
	
	@Column(name = "M14_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M14_3;//添加人id

	//公司内部审批
	@Column(name = "M15", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M15;//安技员意见
	
	@Column(name = "M15_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M15_1;//安技员签字（图片）
	
	@Column(name = "M15_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M15_2;//安技员确认时间
	
	@Column(name = "M15_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M15_3;//安技员id

	@Column(name = "M15_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M15_4;//审批结果（通过，不通过）
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M16;//分厂安全分管领导意见
	
	@Column(name = "M16_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M16_1;//分厂安全分管领导签字（图片）

	@Column(name = "M16_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M16_2;//分厂安全分管领导确认时间

	@Column(name = "M16_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M16_3;//分厂安全分管领导id

	@Column(name = "M16_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M16_4;//审批结果（通过，不通过）

	@Column(name = "M17", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M17;//安全处分管人员意见
	
	@Column(name = "M17_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M17_1;//安全处分管人员签字（图片）

	@Column(name = "M17_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M17_2;//安全处分管人员确认时间

	@Column(name = "M17_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M17_3;//安全处分管人员id

	@Column(name = "M17_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M17_4;//审批结果（通过，不通过）
	
	@Column(name = "M18", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M18;//安全处分管领导意见
	
	@Column(name = "M18_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M18_1;//安全处分管领导签字（图片）

	@Column(name = "M18_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M18_2;//确认时间

	@Column(name = "M18_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M18_3;//安全处分管领导id

	@Column(name = "M18_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M18_4;//审批结果（通过，不通过）	
	
	@Column(name = "M19", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M19;//保卫部意见
	
	@Column(name = "M19_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M19_1;//保卫部签字（图片）

	@Column(name = "M19_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M19_2;//确认时间

	@Column(name = "M19_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M19_3;//保卫部确认人id

	@Column(name = "M19_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M19_4;//审批结果（通过，不通过）	

	@Column(name = "M20", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M20;//部门一把手意见
	
	@Column(name = "M20_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M20_1;//部门一把手签字（图片）

	@Column(name = "M20_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M20_2;//确认时间

	@Column(name = "M20_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M20_3;//部门一把手确认人id

	@Column(name = "M20_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M20_4;//审批结果（通过，不通过）
	
	@Column(name = "M21", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M21;//公司分管领导意见
	
	@Column(name = "M21_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M21_1;//公司分管领导签字（图片）

	@Column(name = "M21_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M21_2;//确认时间

	@Column(name = "M21_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M21_3;//公司分管领导确认人id

	@Column(name = "M21_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M21_4;//审批结果（通过，不通过）	

	@Column(name = "M22", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M22;//能源管控中心意见
	
	@Column(name = "M22_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M22_1;//能源管控中心签字（图片）

	@Column(name = "M22_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M22_2;//确认时间

	@Column(name = "M22_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M22_3;//能源管控中心确认人id

	@Column(name = "M22_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M22_4;//审批结果（通过，不通过）

	//安全措施确认后作业，过程前及过程中分析
	@Column(name = "M23", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M23;//编制时间

	@Column(name = "M23_1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M23_1;//编制人

	@Column(name = "M23_2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M23_2;//编制确认负责人签字（图片）
	
	@Column(name = "M23_3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M23_3;//编制确认许可人签字（图片）

	@Column(name = "M23_4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M23_4;//安全交底（附件）
	
	@Column(name = "M23_5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M23_5;//施工方案（附件）

	@Column(name = "M23_6", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M23_6;//外来单位id（逗号隔开）

	@Column(name = "M23_7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M23_7;//编制确认时间

	@Column(name = "M23_8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M23_8;//编制分析时间
	
	//完工确认
	@Column(name = "M24", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M24;//作业单位完工时间	
	
	@Column(name = "M24_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M24_1;//作业单位完工签字（图片）
	
	@Column(name = "M24_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M24_2;//签字人id

	@Column(name = "M25", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M25;//分厂完工时间	
	
	@Column(name = "M25_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M25_1;//分厂完工签字（图片）

	@Column(name = "M25_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M25_2;//签字人id

	@Column(name = "zt", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String zt;//-2：作废 -1：撤回 0:待编制 1:待作业单位签字 2:待安技员签字 3:待部门一把手签字4:分厂安全分管领导签字5:待安全处分管人员签字6:待安全处分管领导签字7:待保卫部签字8:待公司分管领导签字9：待能源管控中心签字 10：待确认措施 11：待分析 12：待作业单位完工签字  13：待分厂单位完工签字 14：完工
}
