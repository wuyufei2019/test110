package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @ClassName: Sbgl_YfbyndjhEntity
 * @Description: 设备管理-预防保养年度计划
 *
 */
@Entity
@Table(name="sbgl_yfbyndjhentity")
public class Sbgl_YfbyndjhEntity extends BaseEntity {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    @Column(name = "qyid", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    private long qyid;//企业id

    @Column(name = "m1", nullable = true,columnDefinition="varchar(50)")
    @Setter
    @Getter
    private String m1;//项目名称

    @Column(name = "m2", nullable = true,columnDefinition="varchar(50)")
    @Setter
    @Getter
    private String m2;//检修内容

    @Column(name = "m3", nullable = true,columnDefinition="varchar(150)")
    @Setter
    @Getter
    private String m3;//检修方案

    @Column(name = "m4", nullable = true,columnDefinition="varchar(150)")
    @Setter
    @Getter
    private String m4;//检修人员

    @Column(name = "m5", nullable = true,columnDefinition="varchar(200)")
    @Setter
    @Getter
    private String m5;//安全措施

    @Column(name = "m6", nullable = true,columnDefinition="varchar(50)")
    @Setter
    @Getter
    private String m6;//检修质量

    @Column(name = "m7", nullable = true,columnDefinition="varchar(50)")
    @Setter
    @Getter
    private String m7;//检修进度

    @Column(name = "m8", nullable = true,columnDefinition="varchar(50)")
    @Setter
    @Getter
    private String m8;//检修年度


}
