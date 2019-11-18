package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 企业基本信息-抛丸信息
 *
 */
@Entity
@Table(name="bis_ballblast")
public class BIS_BallBlastEntity extends BaseEntity {
	
	@Column(name = "id1", nullable = true, length = 8)
	@Setter
	@Getter
	private long id1;//企业编号
	
	
	@Column(name = "m1", nullable = true)
	@Getter
	@Setter
	private Integer m1;//设备型号(1：立式 2：卧式 3：手动)
	
	@Column(name="m2", nullable=true )
	@Setter
	@Getter
	private Integer m2;//台数
	
	@Column(name="m3", nullable=true )
	@Setter
	@Getter
	private Integer m3;//作业区域人数
	
	@Column(name = "m4", nullable = true, columnDefinition="varchar(500)")
	@Getter
	@Setter
	private String m4;//集尘器位置
	
	@Column(name = "m5", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m5;//产品材质
	
	@Column(name = "m6", nullable = true, columnDefinition="varchar(200)")
	@Getter
	@Setter
	private String m6;//砂丸材质
	
	@Column(name = "m7", nullable = true)
	@Getter
	@Setter
	private Integer m7;//清理制度(0：有 1：无)
	
	@Column(name = "m8", nullable = true)
	@Getter
	@Setter
	private Integer m8;//清理记录(0：有 1：无)

	
}
