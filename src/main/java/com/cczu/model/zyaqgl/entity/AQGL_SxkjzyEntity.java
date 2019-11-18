package com.cczu.model.zyaqgl.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQGL_SxkjzyEntity
 * @Description: 安全管理-受限空间作业
 * @author zpc
 * @date 2018年1月2日
 *
 */
@Entity
@Table(name="aqgl_sxkjzy")
public class AQGL_SxkjzyEntity extends BaseEntity {
	
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
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M0;//作业证编号
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M1;//申请单位
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M2;//受限空间所属单位
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M3;//受限空间名称
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String M4;//作业内容
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M5;//受限空间内介质名称
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M6;//作业时间起
	
	@Column(name = "M7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M7;//作业时间止
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M8;//涉及的其他特殊作业
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M9;//危害辨识
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M10;//作业单位负责人
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M11;//作业人

	@Column(name = "M12", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M12;//监护人
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M13;//实施安全教育人
	
	@Column(name = "M13_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M13_1;//实施时间
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M14;//分析人
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M15;//安全措施编制人
	
	@Column(name = "M15_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M15_1;//编制时间
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M16;//安全措施确认人
	
	@Column(name = "M16_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M16_1;//确认时间
	
	@Column(name = "M17", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M17;//申请单位负责人
	
	@Column(name = "M18", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M18;//审批人
	
	@Column(name = "M19", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M19;//验收人
	
	@Column(name = "M20", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M20;//rfid
	
	@Column(name = "M21", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M21;//申请单位意见
	
	@Column(name = "M22", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M22;//确认时间
	
	@Column(name = "M23", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M23;//审批单位意见
	
	@Column(name = "M24", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M24;//审批时间
	
	@Column(name = "M25", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M25;//验收时间
	
	@Column(name = "M26", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter	
	private String M26;//图片/视频
	
	@Column(name = "M27", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M27;//分配任务人
	
	@Column(name = "M27_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M27_1;//分配任务时间
	
	@Column(name = "M28", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M28;//关闭人
	
	@Column(name = "M28_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M28_1;//关闭时间
	
	@Column(name = "M29", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M29;//有毒有害介质
	
	@Column(name = "M30", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M30;//可燃气
	
	@Column(name = "M31", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M31;//氧含量
	
	@Column(name = "zt", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String zt;//0:已申请 1:已分配任务2:已分析3:已编制安全措施4:已确认安全措施5:已实施安全教育6:申请单位确认7:已审批8:已验收9：已关闭

    @Column(name = "PROCESS_INSTANCE_ID")
    @Setter
    @Getter
    private String processInstanceId;//流程实例id

    @Column(name = "status")
    @Setter
    @Getter
    private String status;//-1 :驳回 0:未提交 1：以提交未审批 2：审批中 3 审批结束
}
