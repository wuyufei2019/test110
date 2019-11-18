package com.cczu.model.entity;


import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 
 * @ClassName: BEIAN_RoadTransportEntity
 * @Description: 道路运输证管理
 * @author wbth
 * @date 2018年8月24日
 *
 */
@Entity
@Table(name="beian_roadtransport")
public class BEIAN_RoadTransportEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1268980284957383394L;
	
	@Column(name = "m1", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m1;//业户名称
	
	@Column(name = "m2", nullable = false, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String m2;//地址
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m3;//车辆号牌
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String m4;//道路运输证号
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m5;//车辆类型
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m6;//核定载质量
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String m7;//车辆(毫米)
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String m8;//经营范围
	
	@Column(name = "m9", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp m9;//发证日期
	
	@Column(name = "m10", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp m10;//有效期
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter	
	private String m11;//备注
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String m12;//上传照片
	
	@Column(name = "userid", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public Integer userid;//录入的用户id
	
	@Column(name = "state", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    private Integer state;//订单状态 0:未审核 1：审核通过 2:审核不通过
}
