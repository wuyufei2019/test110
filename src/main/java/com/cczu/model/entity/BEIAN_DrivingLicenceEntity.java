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
 * @ClassName: BEIAN_DrivingLicenceEntity
 * @Description: 驾驶证管理
 * @author wbth
 * @date 2018年8月24日
 *
 */
@Entity
@Table(name="beian_drivinglicence")
public class BEIAN_DrivingLicenceEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1268980284957383394L;
	
	@Column(name = "m1", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m1;//驾驶证号码
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m2;//姓名
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m3;//性别
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m4;//国籍
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter
	private String m5;//住址
	
	@Column(name = "m6", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter
	private Timestamp m6;//出生日期
	
	@Column(name = "m7", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp m7;//初次领证日期
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m8;//准驾车型
	
	@Column(name = "m9", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp m9;//有效期
	
	@Column(name = "m10", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter	
	private String m10;//备注
	
	@Column(name = "m11", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String m11;//上传图片
	

	@Column(name = "m12", nullable = true, columnDefinition="varchar(200)")
	@Setter
	@Getter	
	private String m12;//联系电话
	
	
	@Column(name = "userid", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public Integer userid;//录入的用户id
	
	@Column(name = "state", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    private Integer state;//订单状态 0:未审核 1：审核通过 2:审核不通过
}
