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
 * @ClassName: AQGL_SxkjzyEntity
 * @Description: 安全管理-受限空间作业
 * @author zpc
 * @date 2018年1月2日
 *
 */
@Entity
@Table(name="ztaqgl_sxkjzy")
public class ZTAQGL_SxkjzyEntity extends BaseEntity {
	
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
	public Long ID2;//申请人
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M1;//作业证编号
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M2;//申请单位
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M3;//申请人
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M4;//受限空间名称
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M5;//受限空间内原有介质名称
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M6;//作业内容
	
	@Column(name = "M7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M7;//作业时间起
	
	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M8;//作业时间止
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M9;//作业单位负责人

	@Column(name = "M10", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M10;//安全监护人

	@Column(name = "M11", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M11;//实施安全教育人
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M12;//作业人

	@Column(name = "M13", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M13;//有限空间等级（一般，特殊）

	@Column(name = "M13_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M13_1;//特殊情况
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M14;//涉及的其他特殊作业

	@Column(name = "M14_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M14_1;//其他特殊作业
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M15;//危害辨识

	@Column(name = "M15_1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M15_1;//其他危害辨识
	
	//作业单位签字
	@Column(name = "M16", nullable = true, columnDefinition="varchar(50)")
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
	private Timestamp M16_2;//作业单位签字时间
	
	@Column(name = "M16_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M16_3;//添加人id

	//公司内部审批
	@Column(name = "M17", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M17;//安技员意见
	
	@Column(name = "M17_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M17_1;//安技员签字（图片）
	
	@Column(name = "M17_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M17_2;//安技员确认时间
	
	@Column(name = "M17_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M17_3;//安技员id

	@Column(name = "M17_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M17_4;//审批结果（通过，不通过）

	@Column(name = "M18", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M18;//安全科长意见
	
	@Column(name = "M18_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M18_1;//安全科长签字（图片）

	@Column(name = "M18_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M18_2;//安全科长确认时间

	@Column(name = "M18_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M18_3;//安全科长id

	@Column(name = "M18_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M18_4;//审批结果（通过，不通过）

	@Column(name = "M19", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M19;//部门一把手意见
	
	@Column(name = "M19_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M19_1;//部门一把手签字（图片）

	@Column(name = "M19_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M19_2;//部门一把手确认时间

	@Column(name = "M19_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M19_3;//部门一把手id

	@Column(name = "M19_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M19_4;//审批结果（通过，不通过）

	@Column(name = "M20", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M20;//安全处分管人员意见
	
	@Column(name = "M20_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M20_1;//安全处分管人员签字（图片）

	@Column(name = "M20_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M20_2;//安全处分管人员确认时间

	@Column(name = "M20_3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M20_3;//安全处分管人员id

	@Column(name = "M20_4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M20_4;//审批结果（通过，不通过）

	//安全措施确认后作业，过程前及过程中分析
	@Column(name = "M21", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M21;//编制时间

	@Column(name = "M21_1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M21_1;//编制人

	@Column(name = "M21_2", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M21_2;//编制确认负责人签字（图片）
	
	@Column(name = "M21_3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M21_3;//编制确认许可人签字（图片）

	@Column(name = "M21_4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M21_4;//安全交底（附件）
	
	@Column(name = "M21_5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M21_5;//施工方案（附件）

	@Column(name = "M21_6", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M21_6;//外来单位id（逗号隔开）

	@Column(name = "M21_7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M21_7;//编制确认时间

	@Column(name = "M21_8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M21_8;//编制分析时间

	//完工确认
	@Column(name = "M22", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M22;//作业单位完工时间	
	
	@Column(name = "M22_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M22_1;//作业单位完工签字（图片）
	
	@Column(name = "M22_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M22_2;//签字人id

	@Column(name = "M23", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M23;//分厂完工时间	
	
	@Column(name = "M23_1", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M23_1;//分厂完工签字（图片）

	@Column(name = "M23_2", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long M23_2;//签字人id

	@Column(name = "M24", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M24;//有毒有害介质
	
	@Column(name = "M25", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M25;//可燃气
	
	@Column(name = "M26", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M26;//氧含量

	@Column(name = "M27", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M27;//单位
	
	@Column(name = "zt", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String zt;//-2：作废 -1：撤回 0:待编制 1:待作业单位签字 2:待安技员签字 3:待部门一把手签字4:待安全科长或分管领导签字5:待安全处分管人员签字 6：待确认措施 7：待分析 8：待作业单位完工签字  9：待分厂单位完工签字 10：完工 
}
