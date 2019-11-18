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
 * @ClassName: EAD_Accident_Gasphysical
 * @Description: 应急辅助决策_事故后果计算&压缩气体物理爆炸
 * @author jason
 *
 */
@Entity
@Table(name="ead_accident_gasphysical")
public class EAD_Accident_Gasphysical implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	public EAD_Accident_Gasphysical() {
	}

	public EAD_Accident_Gasphysical(EAD_AccidentEntity accident, ACA_GasphysicalEntity gasphysical) {
		this.accident = accident;
		this.gasphysical = gasphysical;
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
	@JoinColumn(name = "GASPHYSICAL_ID", nullable = false)
	@Setter
	@Getter
	private ACA_GasphysicalEntity gasphysical;//火球计算
	
}
