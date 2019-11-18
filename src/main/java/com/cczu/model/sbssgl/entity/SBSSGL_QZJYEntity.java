package com.cczu.model.sbssgl.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: SBSSGL_QZJYEntity
 * @Description: 设备管理-强制检验
 * @author 
 * @date 2018年11月29日
 *
 */
@Entity
@Table(name="sbssgl_qzjy")
public class SBSSGL_QZJYEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -3929461343932061100L;

	@Column(name = "sbid", nullable = true, length = 8)
	@Setter
	@Getter
	private long sbid;//特种设备id
	
	@Column(name = "qyid", nullable = true, length = 8)
	@Setter
	@Getter
	public Long qyid;//企业id

	@Column(name = "M1", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M1;//特种设备检测日期
	
	@Column(name = "M2", nullable = true, columnDefinition="datetime")
	@Setter
	@Getter	
	private Timestamp M2;//有效期
	
}
