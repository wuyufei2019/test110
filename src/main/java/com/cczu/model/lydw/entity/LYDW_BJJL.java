package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/*报警记录*/
@Entity
@Table(name = "LYDW_BJJL")
public class LYDW_BJJL implements Serializable {

    private static final long serialVersionUID = 5845668391860587965L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false,columnDefinition="bigint")
    @Setter
    @Getter
    private long id;//记录ID

    @Column(name = "xbid", nullable = true, columnDefinition="varchar(255)")
    @Setter
    @Getter
    private String xbid;//信标id

    @Column(name = "wlid", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    private long wlid;//围栏id

    @Column(name = "type", nullable = true, columnDefinition = "varchar(255)")
    @Setter
    @Getter
    private String type;//报警类型：1.人员串岗 2.人员超限 3.员工报警（PressKey）

    @Column(name = "message", nullable = true, columnDefinition = "varchar(255)")
    @Setter
    @Getter
    private String msg;//报警信息

    @Column(name = "x", nullable = true, columnDefinition = "numeric")
    @Setter
    @Getter
    private float x;//报警定位X坐标

    @Column(name = "y", nullable = true, columnDefinition = "numeric")
    @Setter
    @Getter
    private float y;//报警定位y坐标

    @Column(name = "z", nullable = true, columnDefinition = "numeric")
    @Setter
    @Getter
    private float z;//报警定位z坐标

    @Column(name = "date", nullable = true, columnDefinition = "date")
    @Setter
    @Getter
    private Date time;//报警时间

    @Column(name = "status", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    private int status;//处理状态 0-未处理 1-已处理
}
