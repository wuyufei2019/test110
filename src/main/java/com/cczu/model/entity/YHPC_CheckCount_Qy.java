package com.cczu.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 隐患排查---巡检统计副表企业端
 * @author jason
 */
@Entity
@Table(name="yhpc_checkcount_qy")
public class YHPC_CheckCount_Qy implements Serializable {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = -8266862137595575763L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "qyid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  qyid  ;//企业id
	
	@Column(name = "userid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long userid;//检查人id
	
	@Column(name = "username", nullable = false, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String username;//检查人姓名
	
	@Column(name = "yxcs", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String yxcs;//有效次数

	@Column(name = "yccs", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String yccs;//应查次数

	@Column(name = "bl", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String bl;//巡检率

	@Column(name = "wts", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String wts;//问题数

	@Column(name = "zgs", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String zgs;//整改数
	

}
