package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*定位信息历史记录*/
@Entity
@Table(name = "pub_filetimehis")
public class Pub_FileTimeHis implements Serializable {

    private static final long serialVersionUID = 8605412263073457944L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, columnDefinition="int")
    @Setter
    @Getter
    public int id;//定位历史记录ID

    @Column(name = "tagid", nullable = true, columnDefinition = "varchar(255)")
    @Setter
    @Getter
    public String tagId;//标签ID

    @Column(name = "x", nullable = true, columnDefinition = "float")
    @Setter
    @Getter
    public float x;//x坐标

    @Column(name = "y", nullable = true, columnDefinition = "float")
    @Setter
    @Getter
    public float y;//y坐标

    @Column(name = "z", nullable = true, columnDefinition = "float")
    @Setter
    @Getter
    public float z;//z坐标

    @Column(name = "time", nullable = true, columnDefinition = "datetime")
    @Setter
    @Getter
    public Date time;//更新时间

    @Column(name = "holdingtime", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public float holdingTime;//滞留时间（单位：min）

    @Column(name = "isalarm", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public int isAlarm;//人员报警状态 0-未报警 1-报警
}
