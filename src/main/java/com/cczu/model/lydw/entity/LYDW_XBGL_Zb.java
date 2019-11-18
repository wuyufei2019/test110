package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 蓝牙定位 信标标注坐标
 */
@Entity
@Table(name = "lydw_xbgl_zb")
public class LYDW_XBGL_Zb implements java.io.Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false,columnDefinition="bigint")
    @Setter
    @Getter
    private long id;//ID

    @Column(name = "xbid", nullable = false, columnDefinition="bigint")
    @Setter
    @Getter
    public Long xbid;//信标id

    @Column(name = "X", nullable = true, columnDefinition="varchar(1000)")
    @Setter
    @Getter
    public String X;//

	@Column(name = "Y", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String Y;//

	@Column(name = "Z", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String Z;//

	@Column(name = "M2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String M2;//预留字段
}
