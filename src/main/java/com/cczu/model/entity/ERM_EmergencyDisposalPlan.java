package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @ClassName: erm_emergencydisposalplan
 * @Description: 应急处置方案
 *
 */
@Entity
@Table(name="erm_emergencydisposalplan")
public class ERM_EmergencyDisposalPlan extends BaseEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -7942863682398738928L;

    @Column(name = "ID1", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    public Long ID1;//企业id

    @Column(name = "ID2", nullable = true, columnDefinition="bigint")
    @Setter
    @Getter
    public Long ID2;//操作者id

    @Column(name = "M1", nullable = true, length = 20)
    @Setter
    @Getter
    private String M1;// 等级

    @Column(name = "M2", nullable = true, length = 50)
    @Setter
    @Getter
    private String M2;// 岗位名称

    @Column(name = "M3", nullable = true, length = 2000)
    @Setter
    @Getter
    private String M3;// 生产安全事故处置程序及职责

    @Column(name = "M19", nullable = true, length = 2000)
    @Setter
    @Getter
    private String M19;// 风险提示

    @Column(name = "M20", nullable = true, length = 2000)
    @Setter
    @Getter
    private String M20;// 应急处置方法

    @Column(name = "M4", nullable = true, length = 2000)
    @Setter
    @Getter
    private String M4;// 注意事项

    /**
     * 应急联系方式
     */
    //内部
    @Column(name = "M5", nullable = true, length = 20)
    @Setter
    @Getter
    private String M5;// 联系人岗位

    @Column(name = "M5_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M5_num;// 联系人联系方式

    @Column(name = "M6", nullable = true, length = 20)
    @Setter
    @Getter
    private String M6;// 联系人岗位

    @Column(name = "M6_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M6_num;// 联系人联系方式

    @Column(name = "M7", nullable = true, length = 20)
    @Setter
    @Getter
    private String M7;// 联系人岗位

    @Column(name = "M7_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M7_num;// 联系人联系方式

    @Column(name = "M8", nullable = true, length = 20)
    @Setter
    @Getter
    private String M8;// 联系人岗位

    @Column(name = "M8_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M8_num;// 联系人联系方式

    @Column(name = "M9", nullable = true, length = 20)
    @Setter
    @Getter
    private String M9;// 联系人岗位

    @Column(name = "M9_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M9_num;// 联系人联系方式

    //外部
    @Column(name = "M11", nullable = true, length = 20)
    @Setter
    @Getter
    private String M11;// 联系人岗位

    @Column(name = "M11_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M11_num;// 联系人联系方式

    @Column(name = "M12", nullable = true, length = 20)
    @Setter
    @Getter
    private String M12;// 联系人岗位

    @Column(name = "M12_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M12_num;// 联系人联系方式

    @Column(name = "M13", nullable = true, length = 20)
    @Setter
    @Getter
    private String M13;// 联系人岗位

    @Column(name = "M13_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M13_num;// 联系人联系方式

    @Column(name = "M14", nullable = true, length = 20)
    @Setter
    @Getter
    private String M14;// 联系人岗位

    @Column(name = "M14_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M14_num;// 联系人联系方式

    @Column(name = "M15", nullable = true, length = 20)
    @Setter
    @Getter
    private String M15;// 联系人岗位

    @Column(name = "M15_num", nullable = true, length = 500)
    @Setter
    @Getter
    private String M15_num;// 联系人联系方式





}
