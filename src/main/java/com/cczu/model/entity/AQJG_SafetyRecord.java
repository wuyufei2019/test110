package com.cczu.model.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * @ClassName: AQJG_SafetyRecord
 * @Description: 安全监管_安全备案管理
 *
 */
@Entity
@Table(name="aqjg_saftyrecord")
public class AQJG_SafetyRecord extends BaseEntity {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Getter
	@Setter
	private long ID1;//企业id

	@Column(name = "M1", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M1; //备案编号

	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp M2;//备案日期

	@Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M3;//备案类别

	@Column(name = "M4", nullable = true )
	@Setter
	@Getter
	private Integer M4;//是否审核

	@Column(name="M5", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M5;//审核意见

	@Column(name = "M6", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M6;//上传文件

	@Column(name = "M7", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String M7;//备案经手人

	@Column(name = "M8", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M8;//备注

	@Column(name = "M9", nullable = true, columnDefinition="varchar(2000)")
	@Getter
	@Setter
	private String M9;//应急流程图

	@Column(name = "userid", nullable = true, length = 20)
	@Setter
	@Getter
	public Long userid;//安监用户id

	@Column(name = "expiration", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp expiration;//到期日期

	@Column(name = "agency", nullable = true, columnDefinition="varchar(100)")
	@Getter
	@Setter
	private String agency;//中介公司名称

	@Column(name = "ruletime", nullable = true, columnDefinition="datetime")
	@Getter
	@Setter
	private Timestamp ruletime;//制定日期
}
