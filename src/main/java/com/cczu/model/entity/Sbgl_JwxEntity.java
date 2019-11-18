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
 * @ClassName: Sbgl_JwxEntity
 * @Description: 设备管理-检维修
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="sbgl_jwxentity")
public class Sbgl_JwxEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3929461343932061100L;
	
	@Column(name = "qyid", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long qyid;//企业id

	@Column(name = "ID1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter
	private long ID1;//设备id
	
	@Column(name = "sbtype", nullable = true, columnDefinition="varchar(8)")
	@Setter
	@Getter
	private String sbtype;//设备类别  tzsb：特种设备   scsb：生产设备     aqss：安全设施

	@Column(name = "sbname", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String sbname;//设备名称
	
	@Column(name = "specification", nullable = true,columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String specification;//型号规格
	
	@Column(name = "number", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String number;//出厂编号
	
	@Column(name = "unifynumber", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String unifynumber;//统一编号
	
	@Column(name = "userdept", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String userdept;//使用部门
	
	@Column(name = "operator", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String operator;//操作人员

	@Column(name = "starttime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp starttime;//实际开工检修时间
	
	@Column(name = "endtime", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp endtime;//实际检修竣工时间
	
	@Column(name = "type", nullable = true, columnDefinition="varchar(4)")
	@Setter
	@Getter	
	private String type;//检修类别  1:计划维修□  2: 故障维修□
	
	@Column(name = "content", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String content;//修理内容
	
	@Column(name = "toolfence", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String toolfence;//工具及防护

	@Column(name = "runcondk", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String runcondk ;//运行情况（空负荷）
	
	@Column(name = "runcondd", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String runcondd ;//运行情况（带负荷）
	
	@Column(name = "jfr", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String jfr;//交付人（维修工）
	
	@Column(name = "jsr", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String jsr;//接收人（操作工、技术工艺人员）
	
	@Column(name = "cjzg", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String cjzg;//车间主管
	
	@Column(name = "aqzg", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String aqzg;//安全主管
	
}
