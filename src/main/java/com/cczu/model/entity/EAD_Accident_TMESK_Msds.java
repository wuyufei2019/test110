package com.cczu.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 
 * @ClassName: EAD_Accident_TMESK_Msds
 * @Description: 应急辅助决策_事故后果计算&应急资源管理_应急处置技术（危险化学品信息）
 * @author jason
 *
 */
@Entity
@Table(name="ead_accident_tmesk_msds")
public class EAD_Accident_TMESK_Msds implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public EAD_Accident_TMESK_Msds() {
	}

	public EAD_Accident_TMESK_Msds(EAD_AccidentEntity accident, TMESK_MsdsEntity msds) {
		this.accident = accident;
		this.msds = msds;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, columnDefinition="bigint")
	@Setter
	@Getter
	public long ID;//编号
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCIDENT_ID", nullable = false)
	@Setter
	@Getter
	private EAD_AccidentEntity accident;//事故后果
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MSDS_ID", nullable = false)
	@Setter
	@Getter
	private TMESK_MsdsEntity msds;//应急资源管理_应急处置技术

}
