package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 演练整改记录
 */
@Entity
@Table(name="erm_exercisereformrecordentity")
public class ERM_ExerciseReformRecordEntity extends BaseEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -869831118280016002L;

    @Column(name = "ID1", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    public Long ID1;//企业id

    @Column(name = "ID2", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    public Long ID2;//关联演练记录id

    @Column(name = "M1", nullable = true, length = 100)
    @Setter
    @Getter
    private String M1;//整改人员

    @Column(name = "M2", nullable = true, columnDefinition="varchar(2000)")
    @Setter
    @Getter
    private String M2;//图片

    @Column(name = "M3", nullable = true, length = 100)
    @Setter
    @Getter
    private String M3;//演练记录名称

    @Column(name = "M4", nullable = true, columnDefinition="varchar(2000)")
    @Setter
    @Getter
    private String M4;//整改内容

    @Column(name = "M5", nullable = true, columnDefinition="varchar(2000)")
    @Setter
    @Getter
    private String M5;//附件
}
