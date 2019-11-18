package com.cczu.model.zdgl.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;

import lombok.Getter;
import lombok.Setter;

/**
 * 类别分类
 * @author xj
 */
@Entity
@Table(name="zdgl_lbfl")
public class ZDGL_LbflEntity extends BaseEntity {



	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "id1", nullable = false, columnDefinition="bigint")
	@Getter
	@Setter
	private Long ID1;//企业id

	@Column(name = "pid",columnDefinition="bigint")
	@Getter
	@Setter
	private Long Pid;//父id
	
	@Column(name = "m1", nullable = false, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String M1;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ZDGL_LbflEntity [ID1=");
		builder.append(ID1);
		builder.append(", Pid=");
		builder.append(Pid);
		builder.append(", M1=");
		builder.append(M1);
		builder.append("]");
		return builder.toString();
	}

	
	
}
