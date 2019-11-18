package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*定位信息*/
@Entity
@Table(name = "pub_filetime")
public class Pub_FileTime implements Serializable {

    private static final long serialVersionUID = -7238603443890171463L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition="int")
    @Setter
    @Getter
    public int id;//定位记录ID

    @Column(name = "tagid", nullable = true, columnDefinition = "varchar(255)")
    @Setter
    @Getter
    public String tagId;//标签ID

    @Column(name = "x", nullable = true, columnDefinition = "float")
    @Setter
    @Getter
    public float x;//定位坐标x

    @Column(name = "y", nullable = true, columnDefinition = "float")
    @Setter
    @Getter
    public float y;//定位坐标y 楼层

    @Column(name = "z", nullable = true, columnDefinition = "float")
    @Setter
    @Getter
    public float z;//定位坐标z

    @Column(name = "time", nullable = true, columnDefinition = "datetime")
    @Setter
    @Getter
    public Date time;//更新时间

    @Column(name = "online", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public int onlineStatus;//在线状态 0:离线 1:在线

    @Column(name = "isalarm", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public int isAlarm;//人员是否报警 0-不是 1-是
}
