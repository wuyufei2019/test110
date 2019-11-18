package com.cczu.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

import com.cczu.util.entity.BaseEntity;

/**
 * 
 * @ClassName: BIS_TankEntity
 * @Description: 企业基本信息-储罐
 * @author jason
 * @date 2017年5月27日
 *
 */
@Entity
@Table(name="bis_tank")
public class BIS_TankEntity extends BaseEntity {
	
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 1106837690821192296L;

	@Column(name = "ID1", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID1;//企业编号

	@Column(name = "M4", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M4;//材质
	
	@Column(name = "M5", nullable = true, length = 20)
	@Setter
	@Getter	
	private String M5;//数量
	
	@Column(name = "M6", nullable = true, length = 100)
	@Setter
	@Getter	
	private String M6;//火灾危险性等级
	
	@Column(name = "M7", nullable = true, length = 200)
	@Setter
	@Getter	
	private String M7;//物料名称


	
	@Column(name = "M10", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M10;//罐径
	
	@Column(name = "M11", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M11;//罐高
	
	@Column(name = "M12", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M12;//储罐区面积
	
	@Column(name = "M13", nullable = true, columnDefinition="varchar(2)")
	@Setter
	@Getter	
	private String M13;//有无防火堤 1：有，0：无
	
	@Column(name = "M14", nullable = true, columnDefinition="varchar(100)")
	@Setter
	@Getter	
	private String M14;//防火堤所围面积
	
	@Column(name = "M15", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M15;//现场照片
	
	@Column(name = "M16", nullable = true, columnDefinition="varchar(2000)")
	@Setter
	@Getter
	private String M16;//图纸
	
	@Column(name = "ID2", nullable = true, length = 8)
	@Setter
	@Getter
	private Long ID2;//采集实时容量的设备通道id（TS_DeviceChanel id）
	
	@Column(name = "level1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double level1;//高液位预警
	
	@Column(name = "level2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double level2;//低液位预警
	
	@Column(name = "temperature1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double temperature1;//高温度预警
	
	@Column(name = "temperature2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double temperature2;//低温度预警
	
	@Column(name = "pressure1", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double pressure1;//高压力预警
	
	@Column(name = "pressure2", nullable = true, columnDefinition="float")
	@Setter
	@Getter
	private Double pressure2;//低压力预警
	
	@Column(name = "r1", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r1;//液位点号
	
	@Column(name = "r2", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r2;//温度点号
	
	@Column(name = "r3", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r3;//压力点号
	
	@Column(name = "r4", nullable = true, columnDefinition="varchar(20)")
	@Setter
	@Getter
	private String r4;//流量点号

	@Column(name = "m17", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m17;//温度传感器编号

	@Column(name = "m18", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m18;//压力传感器设备编码

	@Column(name = "m19", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m19;//液位传感器设备编码

	@Column(name = "m20", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m20;//可燃气体传感器设备编码

	@Column(name = "m21", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m21;//有毒气体传感器设备编码

	@Column(name = "m22", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String m22;//所属储罐区
	
	
	
	
	
	@Column(name="jarid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String jarid;//jarId 储罐标识
	
	@Column(name="equipcode", nullable=true, columnDefinition="varchar(19)")
	@Setter
	@Getter
	private String equipcode;// equipCode 设备编码         危险源编码（14位）+设备类型编码(2位)+3位流水码
	
	@Column(name="prodcellid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String prodcellid;// prodcellId 生产单元区域标识
	
	@Column(name="parkid", nullable=true, columnDefinition="char(32)")
	@Setter
	@Getter
	private String parkid;// parkId 所属园区域标识（园区ID）
	
	@Column(name="companycode", nullable=true, columnDefinition="varchar(11)")
	@Setter
	@Getter
	private String companycode;// companyCode 企业编码
	
	@Column(name="districtcode", nullable=true, columnDefinition="varchar(32)")
	@Setter
	@Getter
	private String districtcode;// districtCode 所属行政区划
	
	@Column(name="hazardcode", nullable=true, columnDefinition="varchar(14)")
	@Setter
	@Getter
	private String hazardcode;// hazardCode 重大危险源编码
	
	@Column(name = "M1", nullable = true, columnDefinition="varchar(200)")  //长度改为varchar 200    要改为必填项
	@Setter
	@Getter
	private String M1;//储罐名称          jarName
	
	@Column(name = "M9", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter	
	private String M9;//位号    jarNo 储罐编号
	
	@Column(name = "M8", nullable = true, length = 50)  //长度改为varchar 200  
	@Setter
	@Getter	
	private String M8;//CAS号           casCode        
	
	@Column(name="stormediaename", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String stormediaename;// storMediaEname 储存介质英文名称
	
	@Column(name="jarstatus", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String jarstatus;// jarStatus 储罐状态
	
	@Column(name = "ID3", nullable = true, columnDefinition="varchar(50)")      //    类型长度改为varchar 50  
	@Setter
	@Getter
	private String ID3;//危化品类别          dangeChemKind
	
	@Column(name="dangechemcritical", nullable=true, columnDefinition="numeric(12,2)") 
	@Setter
	@Getter
	private Float dangechemcritical;// dangeChemCritical 危险化学品重大危险源临界量（T） 
	
	@Column(name = "designcapacity", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float designcapacity;//储罐设计储存能力（T）    
	
	@Column(name = "designpressure", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float designpressure;//储罐设计压力
	
	@Column(name = "normalpressuretop", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float normalpressuretop;//正常工作压力上限
	
	@Column(name="pressureunit", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String pressureunit;// 压力计量单位
	
	@Column(name = "normaltemtop", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float normaltemtop;//正常工作温度上限（℃）
	
	@Column(name = "maxmacthick", nullable = true, columnDefinition="numeric(12,2)")
	@Setter
	@Getter
	private Float maxmacthick;//MAC最大允许浓度（有毒ppm）
	
	@Column(name="emerplan", nullable=true, columnDefinition="varchar(1000)")
	@Setter
	@Getter
	private String emerplan;// 应急处置
	
	@Column(name = "criticalmultiplevalue", nullable = true, columnDefinition="numeric(9,2)")
	@Setter
	@Getter
	private Float criticalmultiplevalue;//临界量倍数值
	
	@Column(name="spec", nullable=true, columnDefinition="varchar(100)")
	@Setter
	@Getter
	private String spec;// 规格（D*H）
	
	@Column(name = "M3", nullable = true,  columnDefinition="numeric(12,2)")
	@Setter
	@Getter	
	private Float M3;//容积   designVol
	
	@Column(name="jarmode", nullable=true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String jarmode;//储罐型式          jarmode
	
	@Column(name = "M2", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String M2;//储罐类型         jarType
	
	@Column(name = "longitude", nullable = true, columnDefinition="numeric(18,10)")
	@Setter
	@Getter
	private Float longitude;//经度（度）
	
	@Column(name = "latitude", nullable = true, columnDefinition="numeric(18,10)")
	@Setter
	@Getter
	private Float latitude;//纬度（度）
	
	@Column(name = "isnormal", nullable = true, columnDefinition="varchar(1)")
	@Setter
	@Getter
	private String isnormal;//是否正常状态   0：否；1：是
	
	@Column(name = "chemartid", nullable = true, columnDefinition="varchar(36)")
	@Setter
	@Getter
	private String chemartid;//重点监管危险化工工艺标识
	
	@Column(name = "chemartart", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String chemartart;//重点监管危险化工工艺名称
	
	@Column(name = "creator", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String creator;//创建人
	
	@Column(name = "updator", nullable = true, columnDefinition="varchar(50)")
	@Setter
	@Getter
	private String updator;//修改人
	
	
	
	
	
	
}
