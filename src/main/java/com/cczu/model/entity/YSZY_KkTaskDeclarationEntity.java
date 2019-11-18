package com.cczu.model.entity;

import com.cczu.util.entity.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;

/**
 * @author wbth
 * @ClassName: YSZY_KkzysbEntity
 * @Description: 运输作业管理--卡口作业申报管理
 * @date 2018年8月29日
 */
@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "yszy_kktaskdeclaration")
public class YSZY_KkTaskDeclarationEntity extends BaseEntity {

    private static final long serialVersionUID = 3146428973588126644L;

    public final static int ORDER_STATE_FINISH = 2;
    public final static int ORDER_STATE_UNFINISH = 0;
    public final static int ORDER_STATE_BIND = 1;
    public final static int ORDER_STATE_UNREVIEW = 3;
    public final static int ORDER_STATE_PASS = 4;
    public final static int ORDER_STATE_UNPASS = 5;
    public final static int ORDER_STATE_CHECK = 6;
    public final static int ORDER_STATE_UNCHECK = 7;

    @Column(name = "userid", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    public Integer userid;//录入的用户id

    //----------------托运方-----------
    @Column(name = "entrustCompany", nullable = true, columnDefinition = "varchar(100)")
    @Setter
    @Getter
    private String entrustCompany;//单位名称

    @Column(name = "entrustContact", nullable = true, columnDefinition = "varchar(20)")
    @Setter
    @Getter
    public String entrustContact;//联系电话
    //----------------托运方-----------


    //----------------收货方-----------
    @Column(name = "consigneeCompany", nullable = true, columnDefinition = "varchar(100)")
    @Setter
    @Getter
    private String consigneeCompany;//单位名称

    @Column(name = "consigneeContact", nullable = true, columnDefinition = "varchar(20)")
    @Setter
    @Getter
    public String consigneeContact;//联系电话
    //----------------收货方-----------

    @Column(name = "loadingAddress", nullable = true, columnDefinition = "varchar(200)")
    @Setter
    @Getter
    private String loadingAddress;//装货地点

    @Column(name = "loadingTime", nullable = true, columnDefinition = "datetime")
    @Setter
    @Getter
    private Timestamp loadingTime;//装货时间

    @Column(name = "predictArriveTime", nullable = true, columnDefinition = "datetime")
    @Setter
    @Getter
    private Timestamp predictArriveTime;//预计到达时间


    @Column(name = "transportDestination", nullable = true, columnDefinition = "varchar(200)")
    @Setter
    @Getter
    private String transportDestination;//运输目的地

    //----------------承运方-----------
    @Column(name = "acceptCompany", nullable = true, columnDefinition = "varchar(100)")
    @Setter
    @Getter
    private String acceptCompany;//单位名称

    @Column(name = "businessPermitNum", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    private String businessPermitNum;//经营许可证

    @Column(name = "acceptContact", nullable = true, columnDefinition = "varchar(20)")
    @Setter
    @Getter
    public String acceptContact;//联系电话

    //****车辆信息*****
    @Column(name = "plateNum", nullable = true, columnDefinition = "varchar(30)")
    @Setter
    @Getter
    public String plateNum;//车牌号码
    @Column(name = "wayTransportNum", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String wayTransportNum;//道路运输证号
    //****车辆信息*****

    //****挂车信息*****
    @Column(name = "plateNumG", nullable = true, columnDefinition = "varchar(30)")
    @Setter
    @Getter
    public String plateNumG;//挂车牌号码
    @Column(name = "wayTransportNumG", nullable = true, columnDefinition = "varchar(50)")
    @Setter
    @Getter
    public String wayTransportNumG;//挂道路运输证号
    //****挂车信息*****
    //****驾驶员*****
    @Column(name = "driverName", nullable = true, columnDefinition = "varchar(20)")
    @Setter
    @Getter
    public String driverName;//驾驶员

    @Column(name = "driverContact", nullable = true, columnDefinition = "varchar(20)")
    @Setter
    @Getter
    public String driverContact;//驾驶员联系方式

    @Column(name = "driverDutyRequireNum", nullable = true, columnDefinition = "varchar(30)")
    @Setter
    @Getter
    public String driverDutyRequireNum;//驾驶员从业资格证号
    //****驾驶员*****
    //****押运员*****
    @Column(name = "supercargoName", nullable = true, columnDefinition = "varchar(20)")
    @Setter
    @Getter
    public String supercargoName;//押运员

    @Column(name = "supercargoContact", nullable = true, columnDefinition = "varchar(20)")
    @Setter
    @Getter
    public String supercargoContact;//押运员联系方式

    @Column(name = "supercargoDutyRequireNum", nullable = true, columnDefinition = "varchar(30)")
    @Setter
    @Getter
    public String supercargoDutyRequireNum;//押运员从业资格证号
    //****押运员*****

    //----------------承运方-----------

    @Column(name = "number", nullable = false, columnDefinition = "varchar(100)")
    @Setter
    @Getter
    private String number;//运单号码

    @Column(name = "state", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    private Integer state;//订单状态（0：未绑定，2：已完成, 1:已绑定）  3:未审核 4：审核通过 5:审核不通过  6:检查通过 7：检查不通过

    @Column(name = "type", nullable = true, columnDefinition = "int")
    @Setter
    @Getter
    private Integer type;//订单类型   0 安监局审核普通危化品  3 环保审核危废  4、公安审核易爆易制毒
    
    @Column(name = "attachment", nullable = true, columnDefinition="varchar(1000)")
	@Setter
	@Getter	
	private String attachment;//危险废物经营许可证

    public static final int TYPE_AJ =0;
    public static final int TYPE_HB =3;
    public static final int TYPE_GA =4;

}
