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
 * @Description: 安全风险研判——安全风险研判库
 *
 */
@Entity
@Table(name="aqfxyp_securityriskbase")
public class AQFXYP_SecurityRiskBase extends BaseEntity {

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
    public String M1;//大类

    @Column(name = "M2", nullable = true, length = 500)
    @Setter
    @Getter
    public String M2;//小类

    @Column(name = "M3", nullable = true, length = 500)
    @Setter
    @Getter
    public String M3;//备注
}
