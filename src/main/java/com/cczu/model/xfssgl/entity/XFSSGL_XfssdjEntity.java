package com.cczu.model.xfssgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: XFSSGL_XfssdjEntity
 * @Description: 消防设施登记
 * @author wbth
 * @date 2018年4月21日
 *
 */
@Entity
@Table(name="xfssgl_xfssdj")
public class XFSSGL_XfssdjEntity extends BaseEntity{

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1268980284957383394L;

	@Column(name = "PID",columnDefinition="bigint")
	@Setter
	@Getter
	private Long pid;//父ID	
	
	@Column(name = "type", length = 50)
	@Setter
	@Getter
	private String type;//类别（0类型， 1设施）
	
	@Column(name = "ID1", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	private Long  ID1;//企业ID
	
	@Column(name = "name", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String name;//名称
	
	@Column(name = "ggxh", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String ggxh;//规格型号
	
	@Column(name = "bindcontent", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String bindcontent;//绑定二维码
	
	@Column(name = "x", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String x;//企业平面图坐标X 
	
	@Column(name = "y", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String y;//企业平面图坐标y
	
	@Column(name = "cycle", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String cycle;//换验周期(0:每月/1:每季/2:每半年)
	
	@Column(name = "state", nullable = true, columnDefinition="varchar(10)")
	@Setter
	@Getter	
	private String state;//状态(0:有效/1:过期/2:报废)
	
	@Column(name = "sccs", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String sccs;//生产厂商
	
	@Column(name = "M20", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter	
	private String M20;//备注
	
	@Column(name = "icon", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter	
	private String icon;//图标

}
