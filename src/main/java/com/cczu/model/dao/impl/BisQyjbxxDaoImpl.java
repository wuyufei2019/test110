package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.cczu.model.entity.BIS_EnterpriseEntity;
import com.cczu.model.dao.IBisQyjbxxDao;
import com.cczu.sys.comm.utils.StringUtils;
import com.cczu.util.dao.BaseDao;


@Repository("BisQyjbxxDao")
public class BisQyjbxxDaoImpl extends BaseDao<BIS_EnterpriseEntity, Long> implements IBisQyjbxxDao {

	@Override
	public List<Map<String,Object>> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		String ordercont=setSortWay(mapData,"bis."," order by bis.m44");
		String sql="SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER ("+ordercont+") AS RowNumber,bis.id,bis.m1,bis.m19,bis.m21,"
				+ "bis.m23,bis.m25,bis.m39,bis.m40,bis.m47,bis.m48,bis.m44,bis.m33_3,bis.ktdate,bis.dqdate,bis.state,tv.id1 hastv,fa.id1 as hasfa,bt.id1 as hasbt"
				+ " FROM bis_enterprise bis LEFT JOIN ( SELECT DISTINCT id1 from ts_video) tv on tv.id1= bis.id LEFT JOIN "
				+ "(SELECT DISTINCT id1  FROM fxgk_accidentrisk where s3=0 ) fa on fa.id1= bis.id LEFT JOIN "
				+ "(SELECT DISTINCT id1 from bis_tank where s3=0) bt on bt.id1=bis.id WHERE S3=0 and M1 is not null "+content+" ) "
				+ "AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> dataGridForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY m44) AS RowNumber,id,m1,m11,m11_1,m11_2,m11_3,m5,m6,(case M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (M39= '1') then '是' else '否'end) m39,m19,m44 FROM bis_enterprise bis WHERE S3=0 and M1 is not null "+content+" ) "
				+ "AS a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<BIS_EnterpriseEntity> dataListE(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT * FROM bis_enterprise bis WHERE S3=0 AND M1 is not NULL "+ content ;
		List<BIS_EnterpriseEntity> list=findBySql(sql, null,BIS_EnterpriseEntity.class);
		return list;
	}
	
	@Override
	public List<BIS_EnterpriseEntity> dataList(Map<String, Object> map) {
		String content=content(map);
		String sql=" SELECT ID,S1,S2,S3,M1,M2,M3,CONVERT(varchar(100), M4, 23) M4,M5,M6,M7,M8,M9,M10,M11,M11_1,M11_2,M11_3,M12,M13,M14,M15,M16,"
				+ " M17,M18,M19,M20,M21,M22,M23,M24,M25,M26,M27,M28,M29,M30,M31,M32,M33,M34,M35,"
				+ " M36,M37,M38,M39,M40,M41,M42,M43,M44,ID1,ID2 FROM bis_enterprise bis WHERE S3=0 and M1 is not null "+ content ;
		return findBySql(sql);
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM bis_enterprise bis WHERE S3=0 and M1 is not null"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public BIS_EnterpriseEntity findInfoById(long id) {
		String sql="SELECT * FROM bis_enterprise bis WHERE S3=0 AND ID="+id;
		List<BIS_EnterpriseEntity> list=findBySql(sql, null,BIS_EnterpriseEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Map<String,Object> findInfoNameById(long id) {
		String sql="SELECT a.id as ID,a.m1,a.m2,a.m3,a.m4,a.m5,a.m6,a.m7,a.m8,a.m9,b1.LABEL as m10,"
				+ " a.m11,c1.cname as m12,"
				+ " (case a.m13 when '1' then '中央级' when '2' then '省级' when '3' then '市、地区级' when '4' then '县级' when '5' then '街道、镇、乡级' "
				+ " when '6' then '街道' when '7' then '镇' when '8' then '乡' when '9' then '其他' else '其他' end) as m13,"
				+ " a.m14,"
				+ " (case a.m15 when '1' then '有效' when '2' then '无效' else '' end) as m15,a.m16,a.m17,"
				+ " (case a.m18 when '1' then '一级' when '2' then '二级' when '3' then '三级' when '4' then '达标' when '5' then '未创建' else '' end) as m18,a.m19,a.m20,"
				+ " a.m21,a.m22,a.m23,a.m24,a.m25,a.m26,"
				+ " (case a.m27 when '1' then '是' when '2' then '否' else '' end) as m27,a.m28,a.m29,a.m30,"
				+ " a.m31,a.m32,a.m33,"
				+ " (case a.m34 when '1' then '是' when '2' then '否' else '' end) as m34,"
				+ " (case a.m35 when '1' then '微型' when '2' then '小型' when '3' then '中型' when '4' then '大型' else '' end) as m35,"
				+ " (case M36 when '1' then '工贸' when '2' then '化工' end) as m36,a.m37,a.m38,"
				+ " (case a.m39 when '0' then '否' when '1' then '是' else '' end) as m39,"
				+ " (case a.m40 when '1' then '一级' when '2' then '二级' when '3' then '三级' when '4' then '四级' else '' end) as m40,"
				+ " a.m41,a.m42,a.m43,"
				+ " (case a.m44 when '1' then '红' when '2' then '橙' when '4' then '蓝' when '3' then '黄' else '' end) as m44 "
				+ " FROM bis_enterprise a"
				+ " LEFT JOIN t_dict b1 ON b1.type='jjlx' AND a.m10=b1.value"
				+ " LEFT JOIN tdic_gbtbusiness c1 ON c1.cid=a.m12 "
				+ " WHERE a.S3=0 AND a.ID="+id;
		List<Map<String,Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public void addInfo(BIS_EnterpriseEntity bis) {
//		String sql="INSERT INTO bis_enterprise (S1,S2,S3,M1,M2,M3,M4,M5,M6,M7,M8,M9,M10,M11,M12,M13,M14,M15,M16,M17,M18,M19,M20,M21,ID1) "
//				+ " VALUES (getdate() ,getdate(),0,'"+bis.getM1()+"','"+bis.getM2()+"','"+bis.getM3()+"','"+bis.getM4()+"','"+bis.getM5()+"',"
//				+ " '"+bis.getM6()+"','"+bis.getM7()+"','"+bis.getM8()+"','"+bis.getM9()+"','"+bis.getM10()+"','"+bis.getM11()+"', "
//				+ " '"+bis.getM12()+"' ,'"+bis.getM13()+"','"+bis.getM14()+"','"+bis.getM15()+"','"+bis.getM16()+"','"+bis.getM17()+"', "
//				+ " '"+bis.getM18()+"' ,'"+bis.getM19()+"','"+bis.getM20()+"','"+bis.getM20()+"','"+bis.getID1()+"'  )";
//		updateBySql(sql);
		
	}

	@Override
	public Long addInfore(BIS_EnterpriseEntity bis) {
		save(bis);
		return bis.getID();
	}
	
	@Override
	public void updateInfo(BIS_EnterpriseEntity bis) {
		save(bis);
	}

	@Override
	public void deleteInfo(long id) {
		String sql=" UPDATE bis_enterprise SET S3=1 WHERE ID="+id+" ";
		updateBySql(sql);
	}

	@Override
	public List<BIS_EnterpriseEntity> findAlllist() {
		String sql=" SELECT  * FROM  bis_enterprise WHERE S3=0 and M1 is not null ";
		List<BIS_EnterpriseEntity> list=findBySql(sql, null,BIS_EnterpriseEntity.class);
		return list;
	}
	
	@Override
	public List<BIS_EnterpriseEntity> findAlllist(Map<String,Object> map) {
		String content=content(map);
		String sql=" SELECT  * FROM  bis_enterprise bis WHERE S3=0 and M1 is not null " + content;
		List<BIS_EnterpriseEntity> list=findBySql(sql, null,BIS_EnterpriseEntity.class);
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findAllEFlist() {
		List<Map<String,Object>> maplist=new ArrayList<Map<String, Object>>();
		List<BIS_EnterpriseEntity> bislist= findAlllist();
		
		for (BIS_EnterpriseEntity bis : bislist) {
			if(bis.getM1()!=null){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id", bis.getID());
				map.put("title", bis.getM1());
				map.put("address", bis.getM33());
				if(bis.getM16()!=null) map.put("pointx", bis.getM16());
				if(bis.getM17()!=null) map.put("pointy", bis.getM17());
				map.put("isOpen", 0);
				if(bis.getM44()!=null){
					if(bis.getM44().equals("R") ){
						map.put("icon", "/static/model/images/map/bdmap_icon_h01.png");
					}else if(bis.getM44().equals("O") ){
						map.put("icon", "/static/model/images/map/bdmap_icon_c01.png");
					}else if(bis.getM44().equals("B") ){
						map.put("icon", "/static/model/images/map/bdmap_icon_l01.png");
					}
				}else{
					map.put("icon", "");
				}	
				maplist.add(map);
			}
		}
		return maplist;
	}

	@Override
	public BIS_EnterpriseEntity findInfoByUserId(long userid) {
		String sql="SELECT * FROM bis_enterprise WHERE S3=0 AND ID1="+userid;
		List<BIS_EnterpriseEntity> list=findBySql(sql, null,BIS_EnterpriseEntity.class);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	
	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		
		if(mapData.get("userid")!=null&&mapData.get("userid")!=""){
			content = content + "AND bis.ID1="+mapData.get("userid")+" "; 
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content + "AND bis.M1 like'%"+mapData.get("qyname")+"%'"; 
		}
		if(mapData.get("equalqynm")!=null&&mapData.get("equalqynm")!=""){
			content = content + " AND bis.M1 ='"+mapData.get("equalqynm")+"' "; 
		}
		if(mapData.get("equalqycode")!=null&&mapData.get("equalqycode")!=""){
			content = content + " AND bis.M3_1 ='"+mapData.get("equalqycode")+"' "; 
		}
		if(mapData.get("xzqy")!=null&&mapData.get("xzqy")!=""){
			if(mapData.get("xj")!=null&&mapData.get("xj").equals("1")){
				content = content + " AND bis.ID2 ='"+mapData.get("xzqy")+"' "; 
			}else{
				content = content + "AND bis.ID2 like'"+mapData.get("xzqy")+"%'"; 
			}
		}
		if(mapData.get("equalxzqy")!=null&&mapData.get("equalxzqy")!=""){
			content = content + "AND bis.id2 ='"+mapData.get("equalxzqy")+"'"; 
		}
		if(mapData.get("datestart")!=null&&mapData.get("datestart")!=""){
			content = content + "AND bis.S1 >='"+mapData.get("datestart")+" 00:00:00"+"' "; 
		}
		if(mapData.get("dateend")!=null&&mapData.get("dateend")!=""){
			content = content + "AND bis.S1 <='"+mapData.get("dateend")+" 23:59:59"+"' "; 
		}
		if(mapData.get("bis_ids")!=null&&mapData.get("bis_ids")!=""){
			content = content + "AND bis.id not in ("+mapData.get("bis_ids")+") ";
		}
		if(mapData.get("qygm")!=null&&mapData.get("qygm")!=""){
			content = content + "AND bis.M34 = '"+mapData.get("qygm")+"' "; 
		}
		if(mapData.get("wxydj")!=null&&mapData.get("wxydj")!=""){
			content = content + "AND bis.M40 = '"+mapData.get("wxydj")+"' "; 
		}
		if(mapData.get("fxdj")!=null&&mapData.get("fxdj")!=""){
			content = content + "AND bis.M44 = '"+mapData.get("fxdj")+"' "; 
		}
		if(mapData.get("bzhdj")!=null&&mapData.get("bzhdj")!=""){
			content = content + "AND bis.M18 = '"+mapData.get("bzhdj")+"' "; 
		}
		if(mapData.get("id")!=null&&mapData.get("id")!=""){
			content = content + "AND bis.id = '"+mapData.get("id")+"' "; 
		}
		if(mapData.get("sheng")!=null&&mapData.get("sheng")!=""){
			content = content + "AND bis.m11 = '"+mapData.get("sheng")+"'"; 
		}
		if(mapData.get("shi")!=null&&mapData.get("shi")!=""){
			content = content + "AND bis.m11_1='"+mapData.get("shi")+" '"; 
		}
		if(mapData.get("xian")!=null&&mapData.get("xian")!=""){
			content = content + "AND bis.m11_2='"+mapData.get("xian")+"' "; 
		}
		if(mapData.get("state")!=null&&mapData.get("state")!=""){
			if ("0".equals(mapData.get("state").toString())) {// 停用
				content = content + "AND bis.state='"+mapData.get("state")+"' "; 
			} else if ("1".equals(mapData.get("state").toString())) {// 启用
				content = content + "AND bis.state is null "; 
			}
		}
		//添加创建者查询条件
		if(mapData.get("cjz")!=null&&mapData.get("cjz")!=""){
			content = content + "AND bis.cjz='"+mapData.get("cjz")+"' "; 
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND bis.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( bis.fid='"+mapData.get("fid")+"' or bis.id='"+mapData.get("fid")+"') "; 
		}
		if(mapData.get("isbloc")!=null&&mapData.get("isbloc")!=""){
			content = content + "AND bis.isbloc='"+mapData.get("isbloc")+"' "; 
		}
		if(mapData.get("ucccode")!=null&&mapData.get("ucccode")!=""){
			content = content + "AND bis.M3_1 ='"+mapData.get("ucccode")+"'";
		}
		return content;
	}
	
	  
	@Override
	public List<BIS_EnterpriseEntity> findAllByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findAllEFlistByUserId(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object> findQYNamelistIds(String ids) {
		// TODO Auto-generated method stub
		String sql="SELECT M1 FROM bis_enterprise WHERE S3=0 AND ID IN ("+ids+")";
		List<Object> list=findBySql(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> dataObjectList(Map<String, Object> map) {
		String content=content(map);
		String sql=" select bis.M1,bis.M1_1,bis.M1_2,bis.M2,"
				+ " (case bis.M2_1 when '01' then '企业法人营业执照' when '02' then '营业执照' when '03' then '个体工商户营业执照' "
				+ " when '04' then '个人独资企业营业执照' when '05' then '个人独资企业分支机构营业执照' when '06' then '合伙企业营业执照' "
				+ " when '07' then '合伙企业分支机构营业执照' when '99' then '其他' else '' end) as M2_1 ,"
				+ " bis.M3,bis.M3_1,CONVERT(varchar(100), bis.M4, 23) M4,bis.M5,bis.M6,bis.M6_1,bis.M6_2,bis.M6_3,bis.M7,bis.M8,bis.M8_1,bis.M8_2,bis.M8_3,bis.M8_4,bis.M8_5,bis.M8_6,bis.M9,td.label M10,"
				+ " bis.M11,bis.M11_1,bis.M11_2,bis.M11_3,gbt.cname M12, "
				+" (case when (bis.M13= '1') then '中央级' "
				+" when (bis.M13= '2') then '省级' "
				+" when (bis.M13= '3') then '市、地区级' "
				+" when (bis.M13= '4') then '县级' "
				+" when (bis.M13= '5') then '街道、镇、乡级' "
				+" when (bis.M13= '6') then '街道' "
				+" when (bis.M13= '7') then '镇' "
				+" when (bis.M13= '8') then '乡' "
				+" when (bis.M13= '9') then '其他' "
				+" else bis.M13 end)M13,bis.M14,(case when (bis.M15= '1') then '有效' else '无效'end)M15,bis.M16,bis.M17, "
				+" (case when (bis.M18= '1') then '一级' when (bis.M18= '2') then '二级' when (bis.M18= '3') then '三级' when (bis.M18= '4') then '达标' else '未创建' end)M18,bis.M19,"
				+ " bis.M20,bis.M21,bis.M22,bis.M23,bis.M24,bis.M25,bis.M26, "
				+" (case when (bis.M27= '1') then '是' "
				+" else '否'end)M27,"
				+" (case when (bis.M46= '1') then '是' else '否'end)M46,bis.M46_1,"
				+" (case when (bis.M47= '1') then '是' else '否'end)M47,"
				+" (case when (bis.M48= '1') then '是' else '否'end)M48,"
				+" (case when (bis.M49= '1') then '是' else '否'end)M49,"
				+ " bis.M28,bis.M29,bis.M30,bis.M30_1,bis.M31,bis.M32,bis.M33,bis.M33_1,bis.M33_2,bis.M33_3,bis.M34, "
				+" (case when (bis.M35= '1') then '微型' "
				+" when (bis.M35= '2') then '小型' "
				+" when (bis.M35= '3') then '中型' "
				+" else '大型'end) M35,(case bis.M36 when '1' then '工贸' when '2' then '化工' end) as M36,"
				+ " (case bis.M36_1 when '1' then 'A' when '2' then 'B' when '3' then 'C' when '4' then 'D' when '5' then '未定级' else '' end) as M36_1,"
				+" substring(bis.M37,charindex('||',bis.M37)+2,len(bis.M37)) M37, "
				+" substring(bis.M38,charindex('||',bis.M38)+2,len(bis.M38)) M38, "
				+" (case when (bis.M39= '1') then '是' "
				+" else '否'end) M39, "
				+" (case when (bis.M40= '1') then '一级' "
				+" when (bis.M40= '2') then '二级' "
				+" when (bis.M40= '3') then '三级' "
				+" else '四级'end) M40,bis.M41,bis.M42,bis.M43, "
				+" (case when (bis.M44= '1') then '红' "
				+" when (bis.M44= '2') then '橙' "
				+" when (bis.M44= '4') then '蓝' "
				+" when (bis.M44= '3') then '黄' "
				+" else ''end) M44,tb.m1 wgname from bis_enterprise bis "
				+" left join t_dict td on td.value=bis.m10 "
				+" left join tdic_gbtbusiness gbt on gbt.cid=bis.m12 "
				+" left join t_barrio tb on bis.id2 = tb.code "
				+" where bis.S3=0 and bis.M1 is not null "+content ;
		List<Map<String, Object>> qlist=findBySql(sql,null,Map.class);
		return qlist;
	}

	@Override
	public List<Map<String, Object>> findMapList(Map<String, Object> mapdata) {
		String content=content(mapdata);
		String type="";
		if(mapdata.get("type")!=null&&mapdata.get("type")!=""){
			type=mapdata.get("type").toString();
			if (type.equals("4")){
				type=" and (m44 is null or m44='' or m44=4)";
			}else
				type=" and m44 ="+type;
		}
		String sql=" SELECT  * FROM  bis_enterprise bis WHERE S3=0 and M1 is not null "+type+content ;
		List<BIS_EnterpriseEntity> bislist=findBySql(sql, null,BIS_EnterpriseEntity.class);
		
		List<Map<String,Object>> maplist=new ArrayList<Map<String, Object>>();
		for (BIS_EnterpriseEntity bis : bislist) {
			if(bis.getM1()!=null){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("id", bis.getID());
				map.put("title", bis.getM1());
				map.put("address", bis.getM33());
				if(bis.getM16()!=null) map.put("pointx", bis.getM16());
				if(bis.getM17()!=null) map.put("pointy", bis.getM17());
				map.put("isOpen", 0);
				if(bis.getM44()!=null){
					if(bis.getM44().equals("1") ){
						map.put("icon", "/static/model/images/map/bdmap_icon_h01.png");
					}else if(bis.getM44().equals("2") ){
						map.put("icon", "/static/model/images/map/bdmap_icon_c01.png");
					}else if(bis.getM44().equals("3") ){
						map.put("icon", "/static/model/images/map/bdmap_icon_y01.png");
					}else if(bis.getM44().equals("4") ){
						map.put("icon", "/static/model/images/map/bdmap_icon_l01.png");
					}else{
						map.put("icon", "/static/model/images/map/bdmap_icon_l01.png");
					}
				}else{
					map.put("icon", "/static/model/images/map/bdmap_icon_l01.png");
				}
				maplist.add(map);
			}
		}
		return maplist;
	}

	@Override
	public List<Map<String, Object>> findZdzdMapList(Map<String, Object> mapdata) {
		String content=content(mapdata);
		String sql="SELECT distinct bis.id,bis.m16 pointx,bis.m17 pointy,bis.m1 title,bis.m33 address,'/static/model/images/map/bdmap_icon_c01.png' icon,'zdjg' type "
				+ "FROM bis_mat a left join bis_enterprise bis on bis.id=a.id1 WHERE a.s3=0 and bis.s3=0 and a.m12=1"
				+ content+"union all "
				+ "SELECT distinct bis.id,bis.m16 pointx,bis.m17 pointy,bis.m1 title,bis.m33 address,'/static/model/images/map/bdmap_icon_y01.png' icon,'wxgy' type FROM "
				+ "bis_dangerprocess b left join bis_enterprise bis on bis.id=b.id1 where bis.S3=0 and b.S3=0 "+content
				+" union all "
				+ "SELECT distinct bis.id,bis.m16 pointx,bis.m17 pointy,bis.m1 title,bis.m33 address,'/static/model/images/map/bdmap_icon_h01.png' icon,'wxy' type "
				+ "FROM bis_hazard h LEFT JOIN bis_enterprise bis on h.id1=bis.id LEFT JOIN bis_hazardidentity hd on hd.id1=h.id "
				+ "WHERE h.s3=0 AND 1=1 "+content;
		List<Map<String,Object>> maplist=findBySql(sql, null,Map.class);
		return maplist;
	}

	@Override
	public List<Map<String, Object>> findQyIdTextList(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT id,m1 text FROM bis_enterprise bis WHERE S3=0 and M1 is not null "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	@Override
	public List<Map<String, Object>> findQyTextList(Map<String, Object> map) {
		String content=content(map);
		String sql="SELECT m1 id,m1 text FROM bis_enterprise bis WHERE S3=0 and M1 is not null "+ content;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findQynmList(String xzqy) {
		String sql="SELECT id as id,m1 as text FROM bis_enterprise WHERE S3=0 and M1 is not null and id2 like'"+xzqy+"%'"; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> findQynmList2(String cjz) {
		String sql="SELECT id as id,m1 as text FROM bis_enterprise WHERE S3=0 and M1 is not null and cjz ="+cjz; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public Map<String, Object> statistics(Map<String, Object> map) {
		String content=" FROM bis_enterprise bis WHERE S3=0 and M1 is not null "+content(map);
		String sql="SELECT * from"
				+" (SELECT COUNT(*) as qygm1 "+content+"AND M34='规上') a1,"
				+" (SELECT COUNT(*) as qygm2 "+content+"AND M34='规下') a2,"
				+" (SELECT COUNT(*) as wxydj1 "+content+"AND M40='1') b1,"
				+" (SELECT COUNT(*) as wxydj2 "+content+"AND M40='2') b2,"
				+" (SELECT COUNT(*) as wxydj3 "+content+"AND M40='3') b3,"
				+" (SELECT COUNT(*) as wxydj4 "+content+"AND M40='4') b4,"
				+" (SELECT COUNT(*) as wxydj5 "+content+"AND M40='') b5,"
				+" (SELECT COUNT(*) as fxdj1 "+content+"AND M44='1') c1,"
				+" (SELECT COUNT(*) as fxdj2 "+content+"AND M44='2') c2,"
				+" (SELECT COUNT(*) as fxdj3 "+content+"AND M44='3') c3,"
				+" (SELECT COUNT(*) as fxdj4 "+content+"AND M44='4') c4,"
				+" (SELECT COUNT(*) as iswxyy "+content+"AND M39='1') d1,"
				+" (SELECT COUNT(*) as iswxyn "+content+"AND (M39!='1' or M39 is null)) d2,"
				+" (SELECT COUNT(*) as iszdjgy "+content+"AND M47='1') e1,"
				+" (SELECT COUNT(*) as iszdjgn "+content+"AND (M47!='1' or M47 is null)) e2,"
				+" (SELECT COUNT(*) as isgwgyy "+content+"AND M48='1') f1,"
		        +" (SELECT COUNT(*) as isgwgyn "+content+"AND (M48!='1' or M48 is null)) f2";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		if(list.size()>0){
			return list.get(0);
		}
		
		return null;
	}

	@Override
	public List<Map<String, Object>> qyxxTotalAll(Map<String, Object> map) {
		String content=content(map);
		String sql =" SELECT TOP "+map.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY bis.m11_3,tuser.id,bis.id) AS RowNumber, bis.id,bis.m1,bis.m11_3,tuser.name,isnull(a.num,0) cj,isnull(b.num,0) wl,isnull(c.num,0) ck,isnull(d.num,0) cg,isnull(e.num,0) tzsb,"+
					" isnull(f.num,0) aqpx,isnull(g.num,0) scsb ,isnull(h.num,0) gygc,isnull(i.num,0) yg,isnull(j.num,0) zyb,isnull(k.num,0) jcbg,"+
					" isnull(l.num,0) zybtj,isnull(m.num,0) wxgy,isnull(n.num,0) wxy,isnull(o.num,0) aqss,isnull(p.num,0) ygtj,"+
					" isnull(q.num,0) fc,isnull(r.num,0) sxkj,isnull(s.num,0) ny,isnull(t.num,0) cbs,isnull(u.num,0) yj,isnull(v.num,0) zybc,isnull(w.num,0) fxd from bis_enterprise bis "+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_workshop WHERE s3=0 group by ID1 ) a on bis.id=a.ID1 "+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_mat WHERE s3=0 group by ID1) b on bis.id=b.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_storage WHERE s3=0 group by ID1) c on bis.id=c.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_tank WHERE s3=0 group by ID1) d on bis.id=d.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_specialequipment WHERE s3=0 group by ID1) e on bis.id=e.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_safetyeducation WHERE s3=0 group by ID1) f on bis.id=f.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_productionfacility WHERE s3=0 group by ID1) g on bis.id=g.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_utilities WHERE s3=0 group by ID1) h on bis.id=h.ID1"+
					" LEFT JOIN (select ID3,COUNT(*) num from bis_employee WHERE s3=0 group by ID3) i on bis.id=i.ID3"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_occupharmexam WHERE s3=0 group by ID1) j on bis.id=j.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_occupharmexamreport WHERE s3=0 group by ID1) k on bis.id=k.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_occupillexam WHERE s3=0 group by ID1) l on bis.id=l.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_dangerprocess WHERE s3=0 group by ID1) m on bis.id=m.ID1"+
					" LEFT JOIN (select a.ID1,COUNT(*) num from bis_hazard a,bis_hazardidentity b WHERE a.s3=0 and b.s3=0 and a.ID=b.ID1 group by a.ID1) n on bis.id=n.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_safetyfacilities WHERE s3=0 group by ID1) o on bis.id=o.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_employeetest WHERE s3=0 group by ID1) p on bis.id=p.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_dust WHERE s3=0 group by ID1) q on bis.id=q.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_confinedspace WHERE s3=0 group by ID1) r on bis.id=r.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_chemical_energy_source WHERE s3=0 group by ID1) s on bis.id=s.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_contractor WHERE s3=0 group by ID1) t on bis.id=t.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_metallurgy WHERE s3=0 group by ID1) u on bis.id=u.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from bis_workorder WHERE s3=0 group by ID1) v on bis.id=v.ID1"+
					" LEFT JOIN (select ID1,COUNT(*) num from fxgk_accidentrisk WHERE s3=0 group by ID1) w on bis.id=w.ID1"+
					" LEFT JOIN t_user tuser on bis.cjz=tuser.ID"+
					" where bis.s3=0 "+content+") AS temp WHERE RowNumber > "+map.get("pageSize")+"*("+map.get("pageNo")+"-1) " ;;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	
	@Override
	public void addDataForModule(Set<String> tables,long qyid) {
		// TODO Auto-generated method stub
		StringBuffer sbr=new StringBuffer();
		for(String tablename:tables){
			if("bis_confinedspace".equals(tablename))
				sbr.append("INSERT INTO "+tablename+"(S1,S2,S3,M5,id1) VALUES(getdate(),getdate(),0,'无数据','"+qyid+"');");
			else if("bis_employee".equals(tablename))
				sbr.append("INSERT INTO "+tablename+"(S1,S2,S3,M5,id3) VALUES(getdate(),getdate(),0,'无数据','"+qyid+"');");
			else if("bis_occupillexam".equals(tablename))
				sbr.append("INSERT INTO "+tablename+"(S1,S2,S3,M2,id1) VALUES(getdate(),getdate(),0,'无数据','"+qyid+"');");
			else
			sbr.append("INSERT INTO "+tablename+"(S1,S2,S3,M1,id1) VALUES(getdate(),getdate(),0,'无数据','"+qyid+"');");
		}
		updateBySql(sbr.toString());
	}


	@Override
	public List<Map<String, Object>> ExportQyxxTotalAll(Map<String, Object> map) {
		String content=content(map);
		String sql =" SELECT  bis.id,bis.m1,bis.m11_3,tuser.name,isnull(a.num,'无') cj,isnull(b.num,'无') wl,isnull(c.num,'无') ck,isnull(d.num,'无') cg,isnull(e.num,'无') tzsb,"+
					" isnull(f.num,'无数据') aqpx,isnull(g.num,'无数据') scsb ,isnull(h.num,'无') gygc,isnull(i.num,'无数据') yg,isnull(j.num,'无') zyb,isnull(k.num,'无') jcbg,"+
					" isnull(l.num,'无数据') zybtj,isnull(m.num,'无') wxgy,isnull(n.num,'无') wxy,isnull(o.num,'无数据') aqss,isnull(p.num,'无数据') ygtj,"+
					" isnull(q.num,'无') fc,isnull(r.num,'无') sxkj,isnull(s.num,'无') ny,isnull(t.num,'无') cbs,isnull(u.num,'无') yj,isnull(v.num,'无') zybc,isnull(w.num,'无') fxd from bis_enterprise bis "+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_workshop WHERE s3=0 group by ID1 ) a on bis.id=a.ID1 "+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_mat WHERE s3=0 group by ID1) b on bis.id=b.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_storage WHERE s3=0 group by ID1) c on bis.id=c.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_tank WHERE s3=0 group by ID1) d on bis.id=d.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_specialequipment WHERE s3=0 group by ID1) e on bis.id=e.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_safetyeducation WHERE s3=0 group by ID1) f on bis.id=f.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_productionfacility WHERE s3=0 group by ID1) g on bis.id=g.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_utilities WHERE s3=0 group by ID1) h on bis.id=h.ID1"+
					" LEFT JOIN (select ID3,CAST(COUNT(*) as varchar(10)) num from bis_employee WHERE s3=0 group by ID3) i on bis.id=i.ID3"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_occupharmexam WHERE s3=0 group by ID1) j on bis.id=j.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_occupharmexamreport WHERE s3=0 group by ID1) k on bis.id=k.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_occupillexam WHERE s3=0 group by ID1) l on bis.id=l.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_dangerprocess WHERE s3=0 group by ID1) m on bis.id=m.ID1"+
					" LEFT JOIN (select a.ID1,CAST(COUNT(*) as varchar(10)) num from bis_hazard a,bis_hazardidentity b WHERE a.s3=0 and b.s3=0 and a.ID=b.ID1 group by a.ID1) n on bis.id=n.ID1 "+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_safetyfacilities WHERE s3=0 group by ID1) o on bis.id=o.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_employeetest WHERE s3=0 group by ID1) p on bis.id=p.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_dust WHERE s3=0 group by ID1) q on bis.id=q.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_confinedspace WHERE s3=0 group by ID1) r on bis.id=r.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_chemical_energy_source WHERE s3=0 group by ID1) s on bis.id=s.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_contractor WHERE s3=0 group by ID1) t on bis.id=t.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_metallurgy WHERE s3=0 group by ID1) u on bis.id=u.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from bis_workorder WHERE s3=0 group by ID1) v on bis.id=v.ID1"+
					" LEFT JOIN (select ID1,CAST(COUNT(*) as varchar(10)) num from fxgk_accidentrisk WHERE s3=0 group by ID1) w on bis.id=w.ID1"+
					" LEFT JOIN t_user tuser on bis.cjz=tuser.ID"+
					" where bis.s3=0 "+content+" ORDER BY bis.m11_3,tuser.id,bis.id";
					
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
	@Override
	public List<Map<String, Object>> qyListForDsfNoDataModule(Map<String, Object> map){
		String content=content(map);
		String sql =" SELECT  bis.id,bis.m1,isnull(a.num,0) cj,isnull(b.num,0) wl,isnull(c.num,0) ck,isnull(d.num,0) cg,isnull(e.num,0) tzsb,"+
				" isnull(f.num,0) aqpx,isnull(g.num,0) scsb ,isnull(h.num,0) gygc,isnull(i.num,0) yg,isnull(j.num,0) zyb,isnull(k.num,0) jcbg,"+
				" isnull(l.num,0) zybtj,isnull(m.num,0) wxgy,isnull(n.num,0) wxy,isnull(o.num,0) aqss,isnull(p.num,0) ygtj,"+
				" isnull(q.num,0) fc,isnull(r.num,0) sxkj,isnull(s.num,0) ny,isnull(t.num,0) cbs,isnull(u.num,0) yj,isnull(v.num,0) zybc from bis_enterprise bis "+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_workshop WHERE s3=0 group by ID1 ) a on bis.id=a.ID1 "+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_mat WHERE s3=0 group by ID1) b on bis.id=b.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_storage WHERE s3=0 group by ID1) c on bis.id=c.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_tank WHERE s3=0 group by ID1) d on bis.id=d.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_specialequipment WHERE s3=0 group by ID1) e on bis.id=e.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_safetyeducation WHERE s3=0 group by ID1) f on bis.id=f.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_productionfacility WHERE s3=0 group by ID1) g on bis.id=g.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_utilities WHERE s3=0 group by ID1) h on bis.id=h.ID1"+
				" LEFT JOIN (select ID3,COUNT(*) num from bis_employee WHERE s3=0 group by ID3) i on bis.id=i.ID3"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_occupharmexam WHERE s3=0 group by ID1) j on bis.id=j.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_occupharmexamreport WHERE s3=0 group by ID1) k on bis.id=k.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_occupillexam WHERE s3=0 group by ID1) l on bis.id=l.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_dangerprocess WHERE s3=0 group by ID1) m on bis.id=m.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_hazard WHERE s3=0 group by ID1) n on bis.id=n.ID1 "+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_safetyfacilities WHERE s3=0 group by ID1) o on bis.id=o.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_employeetest WHERE s3=0 group by ID1) p on bis.id=p.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_dust WHERE s3=0 group by ID1) q on bis.id=q.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_confinedspace WHERE s3=0 group by ID1) r on bis.id=r.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_chemical_energy_source WHERE s3=0 group by ID1) s on bis.id=s.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_contractor WHERE s3=0 group by ID1) t on bis.id=t.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_metallurgy WHERE s3=0 group by ID1) u on bis.id=u.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_workorder WHERE s3=0 group by ID1) v on bis.id=v.ID1"+
				" where bis.s3=0 and (a.num is null or b.num is null or c.num is null or d.num is null or e.num is null or f.num is null "
				+ "or g.num is null or h.num is null or m.num is null or n.num is null or o.num is null or p.num is null or q.num is null "
				+ "or r.num is null or s.num is null or t.num is null or u.num is null or v.num is null or i.num is null or j.num is null "
				+ "or k.num is null or l.num is null)"+content;
		
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}
  
	@Override
	public List<BIS_EnterpriseEntity> findAllQy(String ids) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM bis_enterprise WHERE S3=0 ";
		if(StringUtils.isNotBlank(ids))
			sql+="AND ID in ("+ids+")";
		List<BIS_EnterpriseEntity> list=findBySql(sql, null,BIS_EnterpriseEntity.class);
		return list;
	}

	@Override
	public Map<String, Object> FxdjForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT Sum(CASE s.s1 WHEN 'B' THEN s.s2 ELSE 0 END)B,Sum(CASE s.s1 WHEN 'R' THEN s.s2 ELSE 0 END)R,SUM(CASE s.s1 WHEN 'O' THEN s.s2 ELSE 0 END) O "+
                   "FROM(SELECT m44 s1,Count(m44) s2 FROM bis_enterprise bis WHERE S3=0 and M1 is not null "+content + " GROUP BY m44) s"; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		Map<String,Object> map = list.get(0);
		return map;
	}

	@Override
	public List<Map<String, Object>> XZFxdjForApp(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql="SELECT m11_2 xz,SUM(CASE m44 WHEN 'R' THEN 1 ELSE 0 END)R,SUM(CASE m44 WHEN 'O' THEN 1 ELSE 0 END)O,SUM(CASE m44 WHEN 'B' THEN 1 ELSE 0 END)B "+
                   "FROM bis_enterprise bis WHERE S3=0 and M1 is not null "+content + " GROUP BY m11_2"; 
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list;
	}

	public Map<String,Object> findByQyID(Long qyid) {
		String sql=" select b.M1,b.M1_1,b.M1_2,b.M2,"
				+ " (case b.M2_1 when '01' then '企业法人营业执照' when '02' then '营业执照' when '03' then '个体工商户营业执照' "
				+ " when '04' then '个人独资企业营业执照' when '05' then '个人独资企业分支机构营业执照' when '06' then '合伙企业营业执照' "
				+ " when '07' then '合伙企业分支机构营业执照' when '99' then '其他' else b.M2_1 end) as M2_1 ,"
				+ " b.M3,b.M3_1,CONVERT(varchar(100), b.M4, 23) M4,b.M5,b.M6,b.M6_1,b.M6_2,b.M6_3,b.M7,b.M8,b.M8_1,b.M8_2,b.M8_3,b.M8_4,b.M8_5,b.M8_6,b.M9,td.label M10,"
				+ " b.M11,b.M11_1,b.M11_2,b.M11_3,gbt.cname M12, "
				+" (case when (b.M13= '1') then '中央级' "
				+" when (b.M13= '2') then '省级' "
				+" when (b.M13= '3') then '市、地区级' "
				+" when (b.M13= '4') then '县级' "
				+" when (b.M13= '5') then '街道、镇、乡级' "
				+" when (b.M13= '6') then '街道' "
				+" when (b.M13= '7') then '镇' "
				+" when (b.M13= '8') then '乡' "
				+" when (b.M13= '9') then '其他' "
				+" else b.M13 end)M13,b.M14,(case when (b.M15= '1') then '有效' when (b.M15= '2') then '无效' else b.M15 end)M15,b.M16,b.M17, "
				+" (case when (b.M18= '1') then '一级' when (b.M18= '2') then '二级' when (b.M18= '3') then '三级' when (b.M18= '4') then '达标' else '未创建' end)M18,b.M19,"
				+ " b.M20,b.M21,b.M22,b.M23,b.M24,b.M25,b.M26, "
				+" (case when (b.M27= '1') then '是' "
				+" else '否'end)M27,"
				+" (case when (b.M46= '1') then '是' else '否'end)M46,b.M46_1,"
				+" (case when (b.M47= '1') then '是' else '否'end)M47,"
				+" (case when (b.M48= '1') then '是' else '否'end)M48,"
				+" (case when (b.M49= '1') then '是' else '否'end)M49,"
				+ " b.M28,b.M29,b.M30,b.M30_1,b.M31,b.M32,b.M33,b.M33_1,b.M33_2,b.M33_3,(case b.M34 when '1' then '规上' when '2' then '规下' else b.M34 end)M34,"
				+" (case when (b.M35= '1') then '微型' "
				+" when (b.M35= '2') then '小型' "
				+" when (b.M35= '3') then '中型' "
				+" else '大型'end) M35,(case M36 when '1' then '工贸' when '2' then '化工' end) as M36,"
				+ " (case b.M36_1 when '1' then 'A' when '2' then 'B' when '3' then 'C' when '4' then 'D' when '5' then '未定级' else '' end) as M36_1,"
				+" substring(b.M37,charindex('||',b.M37)+2,len(M37)) M37, "
				+" substring(b.M38,charindex('||',b.M38)+2,len(M38)) M38, "
				+" (case when (b.M39= '1') then '是' "
				+" else '否'end) M39, "
				+" (case when (b.M40= '1') then '一级' "
				+" when (b.M40= '2') then '二级' "
				+" when (b.M40= '3') then '三级' "
				+" else '四级'end) M40,b.M41,b.M42,b.M43, "
				+" (case when (b.M44= '1') then '红' "
				+" when (b.M44= '2') then '橙' "
				+" when (b.M44= '4') then '蓝' "
				+" when (b.M44= '3') then '黄' "
				+" else ''end) M44 from bis_enterprise b "
				+" left join t_dict td on td.value=b.M10 "
				+" left join tdic_gbtbusiness gbt on gbt.cid=b.M12 "
				+" where b.S3=0 and b.M1 is not null and b.id="+qyid;
		List<Map<String,Object>> qlist=findBySql(sql,null,Map.class);
		if(qlist.size()>0){
			return qlist.get(0);
		}
		return null;
	}

	@Override
	public Map<String,Object> findByQyIDForApp(Map<String, Object> mapData) {
		String sql=" select b.M1,b.M1_1,b.M1_2,b.M2,"
				+ " (case b.M2_1 when '01' then '企业法人营业执照' when '02' then '营业执照' when '03' then '个体工商户营业执照' "
				+ " when '04' then '个人独资企业营业执照' when '05' then '个人独资企业分支机构营业执照' when '06' then '合伙企业营业执照' "
				+ " when '07' then '合伙企业分支机构营业执照' when '99' then '其他' else b.M2_1 end) as M2_1 ,"
				+ " b.M3,b.M3_1,CONVERT(varchar(100), b.M4, 23) M4,b.M5,b.M6,b.M6_1,b.M6_2,b.M6_3,b.M7,b.M8,b.M8_1,b.M8_2,b.M8_3,b.M8_4,b.M8_5,b.M8_6,b.M9,td.label M10,"
				+ " b.M11,b.M11_1,b.M11_2,b.M11_3,gbt.cname M12, "
				+" (case when (b.M13= '1') then '中央级' "
				+" when (b.M13= '2') then '省级' "
				+" when (b.M13= '3') then '市、地区级' "
				+" when (b.M13= '4') then '县级' "
				+" when (b.M13= '5') then '街道、镇、乡级' "
				+" when (b.M13= '6') then '街道' "
				+" when (b.M13= '7') then '镇' "
				+" when (b.M13= '8') then '乡' "
				+" when (b.M13= '9') then '其他' "
				+" else b.M13 end)M13,b.M14,(case when (b.M15= '1') then '有效' when (b.M15= '2') then '无效' else b.M15 end)M15,b.M16,b.M17, "
				+" (case when (b.M18= '1') then '一级' when (b.M18= '2') then '二级' when (b.M18= '3') then '三级' when (b.M18= '4') then '达标' else '未创建' end)M18,b.M19,"
				+ " b.M20,b.M21,b.M22,b.M23,b.M24,b.M25,b.M26, "
				+" (case when (b.M27= '1') then '是' "
				+" else '否'end)M27,"
				+" (case when (b.M46= '1') then '是' else '否'end)M46,b.M46_1,"
				+" (case when (b.M47= '1') then '是' else '否'end)M47,"
				+" (case when (b.M48= '1') then '是' else '否'end)M48,"
				+" (case when (b.M49= '1') then '是' else '否'end)M49,"
				+ " b.M28,b.M29,b.M30,b.M30_1,b.M31,b.M32,b.M33,b.M33_1,b.M33_2,b.M33_3,(case b.M34 when '1' then '规上' when '2' then '规下' else b.M34 end)M34,"
				+" (case when (b.M35= '1') then '微型' "
				+" when (b.M35= '2') then '小型' "
				+" when (b.M35= '3') then '中型' "
				+" else '大型'end) M35,(case M36 when '1' then '工贸' when '2' then '化工' end) as M36,"
				+ " (case b.M36_1 when '1' then 'A' when '2' then 'B' when '3' then 'C' when '4' then 'D' when '5' then '未定级' else '' end) as M36_1,"
				+" substring(b.M37,charindex('||',b.M37)+2,len(M37)) M37, "
				+" substring(b.M38,charindex('||',b.M38)+2,len(M38)) M38, "
				+" (case when (b.M39= '1') then '是' "
				+" else '否'end) M39, "
				+" (case when (b.M40= '1') then '一级' "
				+" when (b.M40= '2') then '二级' "
				+" when (b.M40= '3') then '三级' "
				+" else '四级'end) M40,b.M41,b.M42,b.M43, "
				+" (case when (b.M44= '1') then '红' "
				+" when (b.M44= '2') then '橙' "
				+" when (b.M44= '4') then '蓝' "
				+" when (b.M44= '3') then '黄' "
				+" else ''end) M44,M45 from bis_enterprise b "
				+" left join t_dict td on td.value=b.M10 "
				+" left join tdic_gbtbusiness gbt on gbt.cid=b.M12 "
				+" where b.S3=0 and b.M1 is not null and b.id="+mapData.get("qyid");
		List<Map<String,Object>> qlist=findBySql(sql,null,Map.class);
		if(qlist.size()>0){
			return qlist.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> findQyInfoByPoint(Map<String, Object> map) {
		// TODO Auto-generated method stub
		String content=" and m16='"+map.get("lng")+"' and m17='"+map.get("lat")+"'";
		if(map.get("xzqy")!=null&&map.get("xzqy")!=""){
			content = content + " AND id2 like'"+map.get("xzqy")+"%'"; 
		}
		String sql="select id,M1 title,M33 address,M33_3 from bis_enterprise bis where s3=0"+content;
		List<Map<String,Object>> list=findBySql(sql,null,Map.class);
		return list;
	}

	public Map<String, Object> getTotalViewById(Long qyid) {
		String sql =" SELECT bis.id,isnull(w.num,0) fxd,isnull(a.num,0) cj,isnull(b.num,0) wl,isnull(c.num,0) ck,isnull(d.num,0) cg,isnull(x.num,0) pjbg,"+
				" isnull(e.num,0) tzsb,isnull(i.num,0) yg,isnull(f.num,0) aqpx,isnull(g.num,0) scsb,isnull(h.num,0) gygc,isnull(m.num,0) wxgy,isnull(o.num,0) aqss,"+
				" isnull(j.num,0) zyb,isnull(k.num,0) jcbg,isnull(l.num,0) zybtj,isnull(p.num,0) ygtj,isnull(n.num,0) wxy,"+
				" isnull(q.num,0) fc,isnull(r.num,0) sxkj,isnull(s.num,0) ny,isnull(t.num,0) cbs,isnull(u.num,0) yj,isnull(v.num,0) zybc from bis_enterprise bis "+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_workshop WHERE s3=0 group by ID1 ) a on bis.id=a.ID1 "+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_mat WHERE s3=0 group by ID1) b on bis.id=b.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_storage WHERE s3=0 group by ID1) c on bis.id=c.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_tank WHERE s3=0 group by ID1) d on bis.id=d.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_specialequipment WHERE s3=0 group by ID1) e on bis.id=e.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_safetyeducation WHERE s3=0 group by ID1) f on bis.id=f.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_productionfacility WHERE s3=0 group by ID1) g on bis.id=g.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_utilities WHERE s3=0 group by ID1) h on bis.id=h.ID1"+
				" LEFT JOIN (select ID3,COUNT(*) num from bis_employee WHERE s3=0 group by ID3) i on bis.id=i.ID3"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_occupharmexam WHERE s3=0 group by ID1) j on bis.id=j.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_occupharmexamreport WHERE s3=0 group by ID1) k on bis.id=k.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_occupillexam WHERE s3=0 group by ID1) l on bis.id=l.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_dangerprocess WHERE s3=0 group by ID1) m on bis.id=m.ID1"+
				" LEFT JOIN (select a.ID1,COUNT(*) num from bis_hazard a,bis_hazardidentity b WHERE a.s3=0 and b.s3=0 and a.ID=b.ID1 group by a.ID1) n on bis.id=n.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_safetyfacilities WHERE s3=0 group by ID1) o on bis.id=o.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_employeetest WHERE s3=0 group by ID1) p on bis.id=p.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_dust WHERE s3=0 group by ID1) q on bis.id=q.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_confinedspace WHERE s3=0 group by ID1) r on bis.id=r.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_chemical_energy_source WHERE s3=0 group by ID1) s on bis.id=s.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_contractor WHERE s3=0 group by ID1) t on bis.id=t.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_metallurgy WHERE s3=0 group by ID1) u on bis.id=u.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from bis_workorder WHERE s3=0 group by ID1) v on bis.id=v.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from fxgk_accidentrisk WHERE s3=0 group by ID1) w on bis.id=w.ID1"+
				" LEFT JOIN (select ID1,COUNT(*) num from aqjg_saftyrecord WHERE s3=0 and (m3='安全评价报告' or m3='应急预案' or m3='职业病危害报告') group by ID1) x on bis.id=x.ID1"+
				" where bis.id = "+qyid;
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}

	@Override
	public Map<String, Object> findwcd(Long qyid){
		String sql =" select Round(CAST(sum(a) AS float) / 17 * 100, 2) wcd from ("+
				" select isnull((select top(1) 1 from bis_workshop where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_mat where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_storage where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_tank where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_metallurgy where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_dust where s3=0 and id1=29), 0) a union" +
				" select isnull((select top(1) 1 from bis_confinedspace where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_workorder where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_employee where s3=0 and id3=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_fieldsupply where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_gas where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_ballblast where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_dangerprocess where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_contractor where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_chemical_energy_source where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from bis_hazard where s3=0 and id1=29), 0) a union"+
				" select isnull((select top(1) 1 from aqjg_saftyrecord where s3=0 and id1=29), 0) a)a";
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		return list.get(0);
	}
}
