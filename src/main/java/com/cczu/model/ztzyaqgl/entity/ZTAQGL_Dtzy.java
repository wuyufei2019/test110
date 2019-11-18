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
 * @ClassName: AQGL_Dtzy
 * @Description: 安全管理-动土作业
 *
 */
@Entity
@Table(name="ztaqgl_dtzy")
public class ZTAQGL_Dtzy extends BaseEntity {
	
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

	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4;//监护人
	
	@Column(name = "M5", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M5;//作业时间起
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M6;//作业时间止

	@Column(name = "M7", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M7;//作业地点

	@Column(name = "M8", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8;//作业单位
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9;//实施安全教育人

	@Column(name = "M10", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M10;//特殊作业

	@Column(name = "M10_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M10_1;//其他特殊作业
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M11;//危害辨识

	@Column(name = "M11_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M11_1;//其他危害辨识

	@Column(name = "M12", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M12;//作业内容（范围、内容、方式深度、面积）
	
	@Column(name = "M12_1", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter	
	private String M12_1;//简附图
	
	
	//作业单位签字
	@Column(name = "M13", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M13;//作业单位意见
	
	@Column(name = "M13_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M13_1;//作业单位签字（图片）
	
	@Column(name = "M13_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M13_2;//作业单位确认时间
	
	@Column(name = "M13_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M13_3;//添加人id
	
	
	//公司内部审批
	@Column(name = "M14", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M14;//能控中心意见
	
	@Column(name = "M14_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M14_1;//能控中心签字（图片）
	
	@Column(name = "M14_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M14_2;//能控中心确认时间
	
	@Column(name = "M14_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M14_3;//能控中心id

	@Column(name = "M14_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M14_4;//审批结果（通过，不通过）
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M15;//分厂意见
	
	@Column(name = "M15_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M15_1;//分厂签字（图片）

	@Column(name = "M15_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M15_2;//分厂确认时间

	@Column(name = "M15_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M15_3;//分厂签字人id

	@Column(name = "M15_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M15_4;//审批结果（通过，不通过）

	@Column(name = "M16", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M16;//设备处意见
	
	@Column(name = "M16_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M16_1;//设备处签字（图片）

	@Column(name = "M16_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M16_2;//设备处确认时间

	@Column(name = "M16_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M16_3;//设备处签字人id

	@Column(name = "M16_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M16_4;//审批结果（通过，不通过）
	
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M17;//保卫处意见
	
	@Column(name = "M17_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M17_1;//保卫处签字（图片）

	@Column(name = "M17_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M17_2;//保卫处确认时间

	@Column(name = "M17_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M17_3;//保卫处签字人id

	@Column(name = "M17_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M17_4;//审批结果（通过，不通过）
	
	@Column(name = "M18", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M18;//安全处意见
	
	@Column(name = "M18_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M18_1;//安全处签字（图片）

	@Column(name = "M18_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M18_2;//安全处确认时间

	@Column(name = "M18_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M18_3;//安全处签字人id

	@Column(name = "M18_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M18_4;//审批结果（通过，不通过）	
	
	//安全措施确认后作业，过程前及过程中分析
	@Column(name = "M19", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M19;//编制时间

	@Column(name = "M19_1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M19_1;//编制人

	@Column(name = "M19_2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M19_2;//编制确认负责人签字（图片）
	
	@Column(name = "M19_3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M19_3;//编制确认许可人签字（图片）

	@Column(name = "M19_4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M19_4;//安全交底（附件）
	
	@Column(name = "M19_5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M19_5;//施工方案（附件）

	@Column(name = "M19_6", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M19_6;//外来单位id（逗号隔开）

	@Column(name = "M19_7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M19_7;//编制确认时间
	
	//完工确认
	@Column(name = "M20", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M20;//作业单位完工时间	
	
	@Column(name = "M20_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M20_1;//作业单位完工签字（图片）
	
	@Column(name = "M20_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M20_2;//签字人id

	@Column(name = "M21", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M21;//分厂完工时间	
	
	@Column(name = "M21_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M21_1;//分厂完工签字（图片）

	@Column(name = "M21_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M21_2;//签字人id
	
	@Column(name = "zt", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String zt;//-2：作废 -1：撤回 0:待编制 1:待作业单位签字 2:待能控中心签字 3:待分厂签字  4:待设备处签字  5：待保卫处签字    6：待安全处签字    7：待确认措施  8：待作业单位完工签字 9：待分厂单位完工签字   10：完工 

	@Column(name = "process", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String process;//审批流程
}
