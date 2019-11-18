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
 * @ClassName: AQGL_ChangeRequest
 * @Description: 安全管理-变更申请
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_changerequest")
public class AQGL_ChangeRequest extends BaseEntity {
	
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
	private String M1;//变更名称
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M2;//变更日期
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M3;//变更说明的技术依据
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M4;//风险分析的管控对策
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M5;//部门意见
	
	@Column(name = "M6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M6;//审批日期
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M7;//审核意见

	@Column(name = "M8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M8;//审核人
	
	@Column(name = "M9", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M9;//审核日期
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M10;//批准意见

	@Column(name = "M11", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M11;//批准人
	
	@Column(name = "M12", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M12;//批准日期

	@Column(name = "M13", nullable = true, columnDefinition = "varchar(2000)")
	@Getter
	@Setter
	private String M13;// 上传技术文件

	@Column(name = "M14", nullable = true, columnDefinition = "varchar(2000)")
	@Getter
	@Setter
	private String M14;// 上传现场照片/视频
}
