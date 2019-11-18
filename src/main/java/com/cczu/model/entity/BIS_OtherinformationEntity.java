package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;


/**
 * 企业基本信息-其他信息
 * @author YZH
 *
 */
@Entity
@Table(name="bis_otherinformation")
public class BIS_OtherinformationEntity extends BaseEntity {



    /**
     *
     */
    private static final long serialVersionUID = 3705753891158824092L;

    @Column(name = "ID1", nullable = true, length = 8)
    @Setter
    @Getter
    private long ID1;//企业编号

    @Column(name = "M1", nullable = true )
    @Setter
    @Getter
    private Integer M1;//现场供气

    @Column(name = "M2", nullable = true, length = 100)
    @Setter
    @Getter
    private String M2;//用途

    @Column(name = "M3", nullable = true )
    @Setter
    @Getter
    private Integer M3;//污水处理

    @Column(name = "M4", nullable = true, length = 100)
    @Setter
    @Getter
    private String M4;//用途

    @Column(name = "M5", nullable = true )
    @Setter
    @Getter
    private Integer M5;//涂装

    @Column(name = "M6", nullable = true, length = 100)
    @Setter
    @Getter
    private String M6;//用途

    @Column(name = "M7", nullable = true )
    @Setter
    @Getter
    private Integer M7;//电镀

    @Column(name = "M8", nullable = true, length = 100)
    @Setter
    @Getter
    private String M8;//用途

    @Column(name = "M9", nullable = true )
    @Setter
    @Getter
    private Integer M9;//阴极氧化

    @Column(name = "M10", nullable = true, length = 100)
    @Setter
    @Getter
    private String M10;//用途

    @Column(name = "M11", nullable = true ,length = 20)
    @Setter
    @Getter
    private String M11;//厂房权属

    @Column(name = "M12", nullable = true )
    @Setter
    @Getter
    private Integer M12;//有协议

    @Column(name = "M13", nullable = true )
    @Setter
    @Getter
    private Integer M13;//锅炉

    @Column(name = "M14", nullable = true, length = 20)
    @Setter
    @Getter
    private String M14;//锅炉原料

    @Column(name = "M15", nullable = true, length = 200)
    @Setter
    @Getter
    private String M15;//备注
}
