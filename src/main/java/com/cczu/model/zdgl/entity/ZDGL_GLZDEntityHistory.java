package com.cczu.model.zdgl.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * 安全制度管理修订记录
 */
@Entity
@Table(name = "zdgl_glzd_history")
public class ZDGL_GLZDEntityHistory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "ID1", nullable = false, columnDefinition="bigint")
    @Setter
    @Getter
    public Long ID1;//企业id

    @Column(name = "ID2", nullable = false, columnDefinition="bigint")
    @Setter
    @Getter
    public Long ID2;//关联安全制度管理id

    @Column(name = "M1", nullable = true, columnDefinition="varchar(200)")
    @Getter
    @Setter
    private String M1;//制度名称

    @Column(name = "M2", nullable = true, columnDefinition="varchar(200)")
    @Getter
    @Setter
    private String M2;//制度编号

    @Column(name = "M3", nullable = true, columnDefinition="varchar(200)")
    @Getter
    @Setter
    private String M3;//版本号

    @Column(name = "M4", nullable = true, columnDefinition="datetime")
    @Getter
    @Setter
    private Timestamp M4;//发布日期

    @Column(name = "M5", nullable = true, columnDefinition="varchar(MAX)")
    @Getter
    @Setter
    private String M5;//制度正文

    @Column(name = "M6", nullable = true, columnDefinition="varchar(200)")
    @Getter
    @Setter
    private String M6;//附件

    @Column(name = "M7", nullable = true, columnDefinition="varchar(500)")
    @Getter
    @Setter
    private String M7;//编辑部门（id集合）

    @Column(name = "M8", nullable = true, columnDefinition="varchar(500)")
    @Getter
    @Setter
    private String M8;//适用部门（id集合）

    @Column(name = "userid", nullable = true, columnDefinition="bigint")
    @Getter
    @Setter
    private Long userid;//修改人员id

    @Column(name = "M15", nullable = true, columnDefinition="varchar(400)")
    @Getter
    @Setter
    private String M15;//附件(pdf)

    @Column(name = "M16", nullable = true, columnDefinition="varchar(400)")
    @Getter
    @Setter
    private String M16;//附件(swf)
}
