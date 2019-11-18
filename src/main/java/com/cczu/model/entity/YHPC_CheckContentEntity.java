package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 隐患排查---检查内容表库
 * @author jason
 * @date 2017年8月18日
 */
@Entity
@Table(name="yhpc_checkcontent")
public class YHPC_CheckContentEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3623104689629734943L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long ID1;//企业id  (企业可以自定义检查内容)

	@Column(name = "dangerlevel", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String dangerlevel ;//隐患级别
	
	@Column(name = "checktitle", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String  checktitle;//检查单元
	
	@Column(name = "content", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String content;//检查内容
	
	@Column(name = "checkyes", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String  checkyes;//有隐患状态  
	
	@Column(name = "checkno", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String checkno;//无隐患状态 
	
	@Column(name = "usetype", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter
	private String usetype ;//用途（1网格巡检/2企业自查） 
	 
}
