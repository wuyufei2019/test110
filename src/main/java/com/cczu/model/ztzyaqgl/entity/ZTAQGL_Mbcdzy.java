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
 * @ClassName: AQGL_Mbcdzy
 * @Description: 安全管理-盲板抽堵作业
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="ztaqgl_mbcdzy")
public class ZTAQGL_Mbcdzy extends BaseEntity {
	
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

	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M4;//设备管道名称

	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M5;//介质

	@Column(name = "M6", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M6;//温度

	@Column(name = "M7", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M7;//压力

	@Column(name = "M8", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M8;//盲板材质
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M9;//盲板规格
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M10;//盲板编号

	@Column(name = "M11", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M11;//实施时间（堵）
	
	@Column(name = "M12", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M12;//实施时间（抽）
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M13;//作业人（抽）

	@Column(name = "M14", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M14;//作业人（堵）

	@Column(name = "M15", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M15;//监护人（抽）

	@Column(name = "M16", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M16;//监护人（堵）
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M17;//生产单位作业指挥

	@Column(name = "M18", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M18;//作业单位负责人
	
	@Column(name = "M19", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M19;//安全监护人

	@Column(name = "M20", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M20;//实施安全教育人

	@Column(name = "M21", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M21;// 涉及的其他特殊作业(1对多)
	
	@Column(name = "M21_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M21_1;// 其他特殊作业

	@Column(name = "M22", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	private String M22;// 盲板位置图
	
	//作业单位签字
	@Column(name = "M23", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M23;//作业单位意见
	
	@Column(name = "M23_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M23_1;//作业单位签字（图片）
	
	@Column(name = "M23_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M23_2;//作业单位确认时间
	
	@Column(name = "M23_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M23_3;//添加人id
	
	
	//公司内部审批
	@Column(name = "M24", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M24;//安技员意见
	
	@Column(name = "M24_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M24_1;//安技员签字（图片）
	
	@Column(name = "M24_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M24_2;//安技员确认时间
	
	@Column(name = "M24_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M24_3;//安技员id

	@Column(name = "M24_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M24_4;//审批结果（通过，不通过）
	
	@Column(name = "M25", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M25;//部门一把手意见
	
	@Column(name = "M25_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M25_1;//部门一把手签字（图片）

	@Column(name = "M25_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M25_2;//部门一把手确认时间

	@Column(name = "M25_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M25_3;//部门一把手id

	@Column(name = "M25_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M25_4;//审批结果（通过，不通过）

	@Column(name = "M26", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M26;//安全处分管领导意见
	
	@Column(name = "M26_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M26_1;//安全处分管领导签字（图片）

	@Column(name = "M26_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M26_2;//安全处分管领导确认时间

	@Column(name = "M26_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M26_3;//安全处分管领导id

	@Column(name = "M26_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M26_4;//审批结果（通过，不通过）
	
	
	@Column(name = "M27", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M27;//保卫部意见
	
	@Column(name = "M27_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M27_1;//保卫部签字（图片）

	@Column(name = "M27_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M27_2;//保卫部确认时间

	@Column(name = "M27_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M27_3;//保卫部id

	@Column(name = "M27_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M27_4;//审批结果（通过，不通过）
	
	
	@Column(name = "M28", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M28;//公司分管领导意见
	
	@Column(name = "M28_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M28_1;//公司分管领导签字（图片）

	@Column(name = "M28_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M28_2;//公司分管领导确认时间

	@Column(name = "M28_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M28_3;//公司分管领导id

	@Column(name = "M28_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M28_4;//审批结果（通过，不通过）
	
	
	//安全措施确认后作业，过程前及过程中分析
	@Column(name = "M29", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M29;//编制时间

	@Column(name = "M29_1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M29_1;//编制人

	@Column(name = "M29_2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M29_2;//编制确认负责人签字（图片）
	
	@Column(name = "M29_3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M29_3;//编制确认许可人签字（图片）

	@Column(name = "M29_4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M29_4;//安全交底（附件）
	
	@Column(name = "M29_5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M29_5;//施工方案（附件）

	@Column(name = "M29_6", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M29_6;//外来单位id（逗号隔开）

	@Column(name = "M29_7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M29_7;//编制确认时间
	
	//完工确认
	@Column(name = "M30", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M30;//作业单位完工时间	
	
	@Column(name = "M30_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M30_1;//作业单位完工签字（图片）
	
	@Column(name = "M30_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M30_2;//签字人id

	@Column(name = "M31", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M31;//分厂完工时间	
	
	@Column(name = "M31_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M31_1;//分厂完工签字（图片）

	@Column(name = "M31_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M31_2;//签字人id

	@Column(name = "zt", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String zt;//-2：作废 -1：撤回 0:待编制 1:待作业单位签字 2:待安技员签字 3:待部门一把手  4：待安全处分管领导  5：待保卫部  6：待公司分管领导   7：待确认措施  8：待作业单位完工签字  9：待分厂单位完工签字  10：完工 
}
