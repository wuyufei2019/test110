package com.cczu.model.lydw.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * 蓝牙定位  员工和工卡关系表
 */
@Entity
@Table(name = "lydw_emp_pubfile")
public class LYDW_Emp_Pubfile implements java.io.Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", unique = true, nullable = false,columnDefinition="bigint")
    @Setter
    @Getter
    private long id;//ID

    @Column(name = "empid", nullable = false, columnDefinition="bigint")
    @Setter
    @Getter
    public Long empid;//员工id

    @Column(name = "fileid", nullable = false, columnDefinition="varchar(255)")
    @Setter
    @Getter
    public String fileid;//工卡id

    @Column(name = "emptype", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public int emptype;//绑定对象类型，0：员工，1：车辆
}
