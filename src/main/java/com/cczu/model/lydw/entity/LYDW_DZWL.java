package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 蓝牙定位 电子围栏
 */
@Entity
@Table(name = "lydw_dzwl")
public class LYDW_DZWL implements java.io.Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false,columnDefinition="bigint")
    @Setter
    @Getter
    private long ID;//ID

    @Column(name = "ID1", unique = true, nullable = false,columnDefinition="bigint")
    @Setter
    @Getter
    private long ID1;//企业id

    @Column(name = "name", nullable = false, columnDefinition="varchar(1000)")
    @Setter
    @Getter
    public String name;//围栏名称

    @Column(name = "floor", nullable = true, columnDefinition="varchar(10)")
    @Setter
    @Getter
    public String floor;//所在楼层

    @Column(name = "floorname", nullable = true, columnDefinition="varchar(500)")
    @Setter
    @Getter
    public String floorname;//所在楼层 名称

    @Column(name = "building", nullable = true, columnDefinition="varchar(500)")
    @Setter
    @Getter
    public String building;//所在建筑

    @Column(name = "buildingname", nullable = true, columnDefinition="varchar(500)")
    @Setter
    @Getter
    public String buildingname;//所在建筑 名称

	@Column(name = "mappoint", nullable = true, columnDefinition="varchar(max)")
	@Setter
	@Getter
	public String mappoint;//围栏坐标  存点位信息 以json格式存储 [{x:'',y:'',z:''},{},{},{}] y 表示高度

	@Column(name = "xbids", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String xbids;//围栏内的信标集合

    @Column(name = "allowids", nullable = true, columnDefinition="varchar(1000)")
    @Setter
    @Getter
    public String allowids;//围栏内 允许出现的信标集合

    @Column(name = "banids", nullable = true, columnDefinition="varchar(1000)")
    @Setter
    @Getter
    public String banids;//围栏内 禁止出现的信标集合

    @Column(name = "points", nullable = true, columnDefinition="varchar(1000)")
    @Setter
    @Getter
    public String points;//存储围栏的所有信息，安装thingJS的格式存储

    @Column(name = "type", nullable = true, columnDefinition="varchar(10)")
    @Setter
    @Getter
    public String type;//存储围栏用于哪种地图 1：thingJS 2：蜂鸟

	@Column(name = "m2", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	public String m2;//预留字段2
}
