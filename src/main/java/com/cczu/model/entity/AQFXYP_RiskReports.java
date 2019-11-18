package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @ClassName: AQFXYP_SecurityRiskBase
 * @Description: 安全风险研判——风险研判报告
 *
 */
@Entity
@Table(name="aqfxyp_riskreports")
public class AQFXYP_RiskReports extends BaseEntity {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -6428335501620710360L;

    @Column(name = "ID1", nullable = true,  columnDefinition="bigint")
    @Setter
    @Getter
    public long ID1;//企业编号

    @Column(name = "ID2", nullable = true,  columnDefinition="bigint")
    @Setter
    @Getter
    public long ID2;//操作者id

    @Column(name = "M1", nullable = true, length = 100)
    @Setter
    @Getter
    public String M1;//等级  公司  车间  班组

    @Column(name = "M2", nullable = true, length = 100)
    @Setter
    @Getter
    public String M2;//车间

    @Column(name = "M3", nullable = true, length = 100)
    @Setter
    @Getter
    public String M3;//班组

    @Column(name = "M4", nullable = true, length = 500)
    @Setter
    @Getter
    public String M4;//风险研判点

    @Column(name = "M5", nullable = true, length = 2000)
    @Setter
    @Getter
    public String M5;//高危。。。状态

    @Column(name = "M6", nullable = true, length = 2000)
    @Setter
    @Getter
    public String M6;//生产装置运行状态

    @Column(name = "M7", nullable = true, length = 2000)
    @Setter
    @Getter
    public String M7;//涉及罐区。。。状态

    @Column(name = "M8", nullable = true, length = 2000)
    @Setter
    @Getter
    public String M8;//班组/车间/公司  安全状态

    @Column(name = "M9", nullable = true, length = 2000)
    @Setter
    @Getter
    public String M9;//班组/车间/公司   承诺

    @Column(name = "M10", nullable = true, length = 20)
    @Setter
    @Getter
    public String M10;//负责人

    @Column(name = "M11", nullable = true, length = 50)
    @Setter
    @Getter
    public String M11;//日期


}
