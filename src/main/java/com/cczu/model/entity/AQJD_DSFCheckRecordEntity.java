package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: AQJD_dsfCheckRecordEntity
 * @Description: 第三方检查表
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="aqjd_dsfcheckrecord")
public class AQJD_DSFCheckRecordEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	// 企业id
	@Column(name = "ID1", nullable = false, columnDefinition = "int")
	@Getter
	@Setter
	private Long ID1;
	
	// 第三方机构id
	@Column(name = "ID2", nullable = false, columnDefinition = "int")
	@Getter
	@Setter
	private Long ID2;

	@Column(name = "M1", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	private String M1;//检查意见

	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//检查时间
	
	@Column(name = "M3", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M3;//整改期限
	
	@Column(name = "M4", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M4;//检查人员
	
	@Column(name = "M5", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M5;//整改负责人
	
	@Column(name = "M6", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M6;//检查图片附件
	
	@Column(name = "M7", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M7;//检查表附件
	
	
	
	
	@Column(name = "M8", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M8;//复查时间
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M9;//复查人员
	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter	
	private String M10;//复查意见
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M11;//复查图片附件
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String M12;//复查表附件
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M13;//检查进度
	
	

}
