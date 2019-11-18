package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 安全生产执法--询问通知书
 * @author zpc
 * @date 2017年08月04日
 */
@Entity
@Table(name="xzcf_enquirynote")
public class XZCF_EnquiryNoteEntity extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5117595642022874311L;

	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID1;//操作人ID
	
	@Column(name = "ID2", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID2;//企业ID
	
	@Column(name = "ID3", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID3;//立案审批ID
	
	@Column(name = "M0", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M0;//检查编号

	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M1;//案由
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//询问时间
	
	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String M3;//询问地址

	@Column(name = "M4", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M4;//额外证件材料
	
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter	
	private String M6;//联系人
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M7;//联系电话
	
	@Column(name = "M8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M8;//材料
}
