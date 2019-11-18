package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author wbth
 * @ClassName: BASIC_CarEntity
 * @Description: 车辆管理
 * @date 2018年8月22日
 */
@Entity
@Table(name = "basic_car")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class BASIC_CarEntity extends BaseEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -1268980284957383394L;

    @Column(name = "m1", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m1;//车型

    @Column(name = "m2", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m2;//车辆品牌

    @Column(name = "m3", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m3;//牵引车牌号
    
    @Column(name = "m13", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m13;//挂车车牌号
    

    @Column(name = "m4", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m4;//车辆吨位

    @Column(name = "m5", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m5;//最大核载人数

    @Column(name = "m6", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m6;//车高

    @Column(name = "m7", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m7;//车长

    @Column(name = "m8", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m8;//车宽

    @Column(name = "m9", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m9;//车辆负责人

    @Column(name = "m10", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String m10;//联系电话

    @Column(name = "m11", nullable = true, columnDefinition = "varchar(255)")
    @Setter
    @Getter
    private String m11;//备注

    @Column(name = "m12", nullable = true, columnDefinition = "varchar(100)")
    @Setter
    @Getter
    private String m12;//保险公司

    @Column(name = "score", nullable = true, columnDefinition = "int default 12")
    @Setter
    @Getter
    private Integer score;//积分

    public BASIC_CarEntity(String m3) {
        this.m3 = m3;
    }

    public BASIC_CarEntity(String m3, String m9, String m10) {
        this.m3 = m3;
        this.m9 = m9;
        this.m10 = m10;
    }

    public BASIC_CarEntity() {
    }
}
