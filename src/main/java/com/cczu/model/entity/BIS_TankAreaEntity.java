package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 
 * @ClassName: BIS_TankAreaEntity
 * @Description: 企业基本信息-储罐区
 * @author wbth
 * @date 2019年8月29日
 *
 */
@Entity
@Table(name="bis_tankarea")
public class BIS_TankAreaEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业编号

	@Column(name = "M1", nullable = true, length = 50)
	@Setter
	@Getter
	private String M1;//储罐区编号
	
	@Column(name = "M2", nullable = true, length = 50)
	@Setter
	@Getter
	private String M2;//储罐区名称
	
	@Column(name = "M3", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M3;//储罐区面积
	
	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M4;//储罐个数
	
	@Column(name = "M5", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M5;//罐间最小距离
	
	@Column(name = "M6", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M6;//lng
	
	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter
	private String M7;//lat

	@Column(name = "M8", nullable = true, length = 500)
	@Setter
	@Getter
	private String M8;//备注

	@Column(name = "M9", nullable = true, length = 10)
	@Setter
	@Getter
	private String M9;//防护堤长度

	@Column(name = "M10", nullable = true, length = 10)
	@Setter
	@Getter
	private String M10;//防护堤宽度

	@Column(name = "M11", nullable = true, length = 10)
	@Setter
	@Getter
	private String M11;//防护堤高度

	@Column(name = "equipcode", nullable = true, length = 100)
	@Setter
	@Getter
	private String equipcode;//设备编码

}
