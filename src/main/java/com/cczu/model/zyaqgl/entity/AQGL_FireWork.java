package com.cczu.model.zyaqgl.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQGL_FireWork
 * @Description: 安全管理-动火作业
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_firework")
public class AQGL_FireWork extends BaseEntity {
	
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

	@Column(name = "applyer", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String applyer;//申请人

	@Column(name = "ID3", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public Long  ID3;//承包商id

	@Column(name = "contractor", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	public String  contractor;//承包商名称

	@Column(name = "M1", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M1;//作业证编号
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M2;//申请单位
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M3;//动火作业级别
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4;//动火方式
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	public String M5;//动火地点
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M6;//动火时间起
	
	@Column(name = "M7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M7;//动火时间止
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M8;//动火作业负责人

	@Column(name = "M8_id", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M8_id;//动火作业负责人ids

	@Column(name = "M9", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M9;//动火人

	@Column(name = "M9_id", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String M9_id;//动火人ids

	@Column(name = "M10", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M10;// 涉及的其他特殊作业(1对多)
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M11;//危害辨识

	@Column(name = "M12", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M12;//附件
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M13;//生产单位负责人
	
	@Column(name = "M13_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M13_1;//生产单位确认时间
	
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
	private String M17;//监火人
	
	@Column(name = "M18_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M18_1;//监火人确认时间
	
	@Column(name = "M19", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M19;//动火初审人
	
	@Column(name = "M20_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M20_1;//动火初审人确认时间
	
	@Column(name = "M21", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M21;//实施安全教育人
	
	@Column(name = "M21_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M21_1;//实施安全教育人确认时间
	
	@Column(name = "M22", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M22;//申请单位负责人
	
	@Column(name = "M22_1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String M22_1;//申请单位意见
	
	@Column(name = "M22_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M22_2;//申请单位确认时间
	
	@Column(name = "M23", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M23;//安全管理部门负责人
	
	@Column(name = "M23_1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String M23_1;//安全管理部门意见
	
	@Column(name = "M23_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M23_2;//负责人确认时间
	
	@Column(name = "M24", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M24;//动火审批人
	
	@Column(name = "M24_1", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String M24_1;//动火审批人意见
	
	@Column(name = "M24_2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M24_2;//动火审批人审批时间
	
	@Column(name = "M24_3", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	public String M24_3;//动火审批人审批照片
	
	@Column(name = "M25", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M25;//验收人
	
	@Column(name = "M25_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M25_1;//验收时间
	
	@Column(name = "M25_2", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	public String M25_2;//验收照片
	
	@Column(name = "M25_3", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	public String M25_3;//手写签名
	
	@Column(name = "M26", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M26;//图片/视频
	
	@Column(name = "M27", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M27;//分配人
	
	@Column(name = "M27_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M27_1;//分配时间
	
	@Column(name = "M28", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String M28;//关闭人
	
	@Column(name = "M28_1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M28_1;//关闭时间
	
	@Column(name = "zt", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	private String zt;//0:已申请1:已分配任务2:已分析3:已编制安全措施4:已确认安全措施5:生产单位已确认6:监火人已确认7:动火初审人已确认8:已实施安全教育9：已申请单位确认10：安全部门已确认11：动火审批人已审批12：已验收13：关闭

	@Column(name = "M29", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String M29;//承包商名称


	@Column(name = "M11_1", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M11_1;//其他-危害辨识

    @Column(name = "PROCESS_DEFINITION_ID")//流程id
    @Setter
    @Getter
    private String processDefinitionId;

    @Column(name = "PROCESS_INSTANCE_ID")//流程实例id
    @Setter
    @Getter
    private String processInstanceId;

    @Transient
    @Setter
    @Getter
    private String taskId;

    @Transient
    @Setter
    @Getter
    private String assigner;

    @Transient
    @Setter
    @Getter
    private String candidateUsers;//候选人

    @Column(name = "status")
    @Setter
    @Getter
    private String status;//-1 :驳回 0:未提交 1：以提交未审批 2：审批中 3 审批结束


}
