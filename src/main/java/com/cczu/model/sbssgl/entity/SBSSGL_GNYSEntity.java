package com.cczu.model.sbssgl.entity;

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
 * 设备设施管理-功能验收
 * @author ZPC
 */
@Entity
@Table(name = "sbssgl_gnys")
public class SBSSGL_GNYSEntity implements Serializable {

	private static final long serialVersionUID = 5739935339538232699L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long ID;//编号
	
	@Column(name = "sbysid", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public Long sbysid;//设备验收id

	@Column(name = "m1", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m1;//测试项目
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m2;//测试结果
}
