package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 蓝牙定位 区域权限
 */
@Entity
@Table(name = "lydw_qyqx")
public class LYDW_QYQX implements java.io.Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false,columnDefinition="bigint")
    @Setter
    @Getter
    private long ID;//ID

    @Column(name = "ID1", unique = false, nullable = false,columnDefinition="bigint")
    @Setter
    @Getter
    private long ID1;//企业id


    @Column(name = "name", nullable = false, columnDefinition="varchar(1000)")
    @Setter
    @Getter
    public String name;//区域名称

    @Column(name = "floor", nullable = true, columnDefinition="varchar(10)")
    @Setter
    @Getter
    public String floor;//所在楼层

	@Column(name = "mappoint", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	public String mappoint;//围栏坐标

	@Column(name = "xbids", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String xbids;//围栏内的信标集合

	@Column(name = "deptnames", nullable = true, columnDefinition="varchar(500)")
	@Setter
	@Getter
	public String deptnames;//允许进出部门
}
