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
 * @ClassName: BEIAN_DrivingPermitEntity
 * @Description: 车辆行驶证管理
 * @author wbth
 * @date 2018年8月22日
 *
 */
@Entity
@Table(name="beian_drivingpermit")
public class BEIAN_DrivingPermitEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1268980284957383394L;
	
	@Column(name = "m1", nullable = false, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m1;//号牌号码
	
	@Column(name = "m2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m2;//车辆类型
	
	@Column(name = "m3", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m3;//所有人
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String m4;//住址
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m5;//使用性质
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m6;//品牌型号
	
	@Column(name = "m7", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m7;//车辆识别代号
	
	@Column(name = "m8", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m8;//发动机号码
	
	@Column(name = "m9", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp m9;//注册日期
	
	@Column(name = "m10", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp m10;//发证日期
	
	@Column(name = "m11", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp m11;//有效期
	
	@Column(name = "m12", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m12;//核定载人数
	
	@Column(name = "m13", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m13;//总质量
	
	@Column(name = "m14", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String m14;//整备质量
	
	@Column(name = "m15", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String m15;//外廓尺寸
	
	@Column(name = "m16", nullable = true, columnDefinition="varchar(255)")
	@Setter
	@Getter	
	private String m16;//备注
	
	@Column(name = "m17", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String m17;//上传照片

	@Column(name = "m18", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m18;//核载总质量
	
	@Column(name = "userid", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public Integer userid;//录入的用户id
	
	@Column(name = "state", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    private Integer state;//订单状态 0:未审核 1：审核通过 2:审核不通过
}
