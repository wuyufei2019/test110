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
 * @ClassName: AQGL_ChangeAcceptance
 * @Description: 安全管理-变更验收
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqgl_changeacceptance")
public class AQGL_ChangeAcceptance extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//企业id
	
	@Column(name = "M1", nullable = true, columnDefinition="bigint")
	@Setter
	@Getter	
	private Long M1;//选择变更项目（下拉选择）
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M2;//验收单位（填写）
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M3;//验收日期
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	public String M4;//验收人员（多选或填写）
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M5;//验收意见（多选或填写）
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M6;//验收结论（合格/不合格）
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M7;//抄送部门（多选）
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(30)")
	@Setter
	@Getter
	public String M8;//记录人
	
	@Column(name = "M9", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M9;//记录日期
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	public String M10;//上传附件
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M11;//审核意见

	@Column(name = "M12", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M12;//审核人
	
	@Column(name = "M13", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M13;//审核日期
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter
	public String M14;//批准意见

	@Column(name = "M15", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	public String M15;//批准人
	
	@Column(name = "M16", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	public Timestamp M16;//批准日期
}
