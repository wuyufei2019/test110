package com.cczu.model.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cczu.model.dao.IDwZyspDao;
import com.cczu.model.entity.DW_WorkApprovalEntity;
import com.cczu.util.dao.BaseDao;

@Repository("DwZyspDao")
public class DwZyspDaoImpl extends BaseDao<DW_WorkApprovalEntity, Long> implements IDwZyspDao {

	@Override
	public void addInfo(DW_WorkApprovalEntity obj) {
		save(obj);
	}

	@Override
	public void updateInfo(DW_WorkApprovalEntity obj) {
		save(obj);
	}

	@Override
	public List<Map<String, Object>> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql="";
		if(!"1".equals(mapData.get("usertype").toString())){//安监
			sql =" SELECT top "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY b.id,a.id desc) AS RowNumber,a.*,b.m1 qyname FROM dw_workapproval a "
					+ " left join bis_enterprise b on a.id1=b.id WHERE a.S3=0 and b.S3=0 "+content+" ) "
					+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}else{
			sql =" SELECT top "+mapData.get("pageSize")+" a.* FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,* FROM dw_workapproval a WHERE a.S3=0 "+content+" ) "
				+ "as a WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) " ;
		}
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);

		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql="";
		if(!"1".equals(mapData.get("usertype").toString())){//安监
			sql="	SELECT COUNT(*) FROM dw_workapproval a left join bis_enterprise b on a.id1=b.id WHERE a.S3=0 and b.S3=0 " + content;
		}else{
			sql = " SELECT COUNT(*) sum  FROM dw_workapproval a WHERE a.s3=0 " + content;
		}
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	/**
	 * 查询条件
	 * 
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData) {

		String content = "";
		if (mapData.get("qyid") != null && !"".equals(mapData.get("qyid"))) {
			content = content + " AND a.ID1 =" + mapData.get("qyid");
		}
		if(mapData.get("qyname")!=null&&mapData.get("qyname")!=""){
			content = content +" AND b.m1 LIKE'%"+mapData.get("qyname")+"%' "; 
		}
		if (mapData.get("xzqy") != null && !"".equals(mapData.get("xzqy"))) {
			content = content + " AND b.ID2 LIKE '" + mapData.get("xzqy") + "%'";
		}
		if (mapData.get("zylx") != null && !"".equals(mapData.get("zylx"))) {
			content = content + " AND a.m1 = '" + mapData.get("zylx") + "'";
		}
		if (mapData.get("zyjb") != null && !"".equals(mapData.get("zyjb"))) {
			content = content + " AND a.m2 = '" + mapData.get("zyjb") + "'";
		}
		if (mapData.get("zydw") != null && !"".equals(mapData.get("zydw"))) {
			content = content + " AND a.m17 = '" + mapData.get("zydw") + "'";
		}
		if (mapData.get("starttime") != null && !"".equals(mapData.get("starttime"))) {
			content = content + " AND '" + mapData.get("starttime") + "' between a.m7 and a.m8";
		}
		//添加监管类型查询条件
		if(mapData.get("jglx")!=null&&mapData.get("jglx")!=""){
			content = content + "AND b.m36='"+mapData.get("jglx")+"' "; 
		}
		//添加集团公司查询条件(集团公司可以看到下属的企业信息)
		if(mapData.get("fid")!=null&&mapData.get("fid")!=""){
			content = content + "AND ( b.fid='"+mapData.get("fid")+"' or b.id='"+mapData.get("fid")+"') "; 
		}
		return content;
	}

	@Override
	public void deleteInfo(Long id) {
		String sql = " UPDATE dw_workapproval SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}

	@Override
	public DW_WorkApprovalEntity findInfoById(Long id) {
		String sql = "SELECT * FROM dw_workapproval a WHERE a.S3=0 AND a.ID=" + id;
		List<DW_WorkApprovalEntity> list = findBySql(sql, null, DW_WorkApprovalEntity.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}
	
	@Override
	public Map<String, Object> findInfoById2(Long id) {
		String sql = "SELECT "
				+ "(case a.m20 when '1' then '特种作业' when '2' then '一般作业' end) zylx,"
				+ " a.m1 zyfs,"
				+ " (case a.m2 when '1' then '特级' when '2' then '一级' when '3' then '二级' when '4' then '其他' end ) zyjb,"
				+ " (case a.m17 when '1' then '外包施工队' when '2' then '本场人员' end ) sgdw,"
				+ " a.* " + " FROM dw_workapproval a" + " WHERE a.S3=0 AND a.ID=" + id;
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	@Override
	public List<Map<String, Object>> getExcelData(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql ="";
		if("1".equals(mapData.get("usertype").toString())){
				sql ="SELECT a.id,(case a.m20 when '1' then '特种作业' when '2' then '一般作业' end) as m20,a.m1 m1,(case a.m2 when '1' then '特级' when '2' then '一级' when '3' then '二级' when '4' then '其他' end )as m2,"
						+ " (case a.m17 when '1' then '外包施工队' when '2' then '本场人员' end )as m17,"
						+ " a.m3,a.m4,a.m5,a.m6,convert(varchar(19),a.m7,120) m7,convert(varchar(19),a.m8,120) m8,a.m9,a.m10,a.m11,a.m18,a.m19 FROM dw_workapproval a WHERE a.s3=0 and a.ID1 is not null " + content;
		}else{
			sql ="SELECT a.id,(case a.m20 when '1' then '特种作业' when '2' then '一般作业' end) as m20,(case a.m1 when '1' then '动火作业' when '2' then '受限空间作业' when '3' then '管道拆卸作业' when '4' then '盲板抽堵作业' when '5' then '高处安全作业' when '6' then '吊装安全作业' "
					+ " when '7' then '临时用电安全作业' when '8' then '动土安全作业' when '9' then '断路安全作业' when '10' then '其他安全作业' else '' end ) as m1,(case a.m2 when '1' then '特级' when '2' then '一级' when '3' then '二级' when '4' then '其他' end )as m2,"
					+ " (case a.m17 when '1' then '外包施工队' when '2' then '本场人员' end )as m17,"
					+ "a.m3,a.m4,a.m5,a.m6,convert(varchar(19),a.m7,120) m7,convert(varchar(19),a.m8,120) m8,a.m9,a.m10,a.m11,b.m1 as qynm ,a.m18,a.m19 FROM dw_workapproval a left join bis_enterprise b on a.ID1=b.id WHERE a.s3=0 and b.S3=0 " + content;
		}
		List<Map<String, Object>> list=findBySql(sql, null,Map.class);
		list=setValue2Name(list);
		return list;
	}

	private List<Map<String, Object>> setValue2Name(List<Map<String, Object>> list){
		for(Map<String, Object> map : list){
		if(map.get("m9")!=null){
			StringBuffer wxys = new StringBuffer();
			String[] arrWxys = map.get("m9").toString().split(",");
			if(arrWxys != null){
				for(int j=0;j<arrWxys.length;j++){
					if(j>0){
						wxys.append(",");
					}
					if ("1".equals(arrWxys[j])){
						wxys.append("易燃易爆物质");
					}else if ("2".equals(arrWxys[j])){
						wxys.append("坠落");
					}else if ("3".equals(arrWxys[j])){
						wxys.append("腐蚀性物质");
					}else if ("4".equals(arrWxys[j])){
						wxys.append("蒸汽");
					}else if ("5".equals(arrWxys[j])){
						wxys.append("高压气体/液体");
					}else if ("6".equals(arrWxys[j])){
						wxys.append("有毒有害物质");
					}else if ("7".equals(arrWxys[j])){
						wxys.append("高温/低温");
					}else if ("8".equals(arrWxys[j])){
						wxys.append("触电");
					}else if ("9".equals(arrWxys[j])){
						wxys.append("窒息");
					}else if ("10".equals(arrWxys[j])){
						wxys.append("噪音");
					}else if ("11".equals(arrWxys[j])){
						wxys.append("产生火花/静电");
					}else if ("12".equals(arrWxys[j])){
						wxys.append("旋转设备");
					}else if ("13".equals(arrWxys[j])){
						wxys.append("机械伤害");
					}else if ("14".equals(arrWxys[j])){
						wxys.append("粉尘");
					}else if ("15".equals(arrWxys[j])){
						wxys.append("不利天气");
					}else if ("16".equals(arrWxys[j])){
						wxys.append("淹没/埋没");
					}else if ("17".equals(arrWxys[j])){
						wxys.append("辐射");
					}else if ("18".equals(arrWxys[j])){
						wxys.append("交叉作业");
					}else if ("19".equals(arrWxys[j])){
						wxys.append("其他危险因素");
					}
				}
				map.put("m9", wxys);
			}
		}
		if(map.get("m11")!=null){
			StringBuffer sglx = new StringBuffer();
			String[] arrSglx = map.get("m11").toString().split(",");
			if(arrSglx != null){
				for(int j=0;j<arrSglx.length;j++){
					if(j>0){
						sglx.append(",");
					}
					if ("1".equals(arrSglx[j])){
						sglx.append("物体打击");
					}else if ("2".equals(arrSglx[j])){
						sglx.append("车辆伤害");
					}else if ("3".equals(arrSglx[j])){
						sglx.append("机械伤害");
					}else if ("4".equals(arrSglx[j])){
						sglx.append("起重伤害");
					}else if ("5".equals(arrSglx[j])){
						sglx.append("触电");
					}else if ("6".equals(arrSglx[j])){
						sglx.append("淹溺");
					}else if ("7".equals(arrSglx[j])){
						sglx.append("灼烫");
					}else if ("8".equals(arrSglx[j])){
						sglx.append("火灾");
					}else if ("9".equals(arrSglx[j])){
						sglx.append("高处坠落");
					}else if ("10".equals(arrSglx[j])){
						sglx.append("坍塌");
					}else if ("11".equals(arrSglx[j])){
						sglx.append("冒顶片帮");
					}else if ("12".equals(arrSglx[j])){
						sglx.append("透水");
					}else if ("13".equals(arrSglx[j])){
						sglx.append("放炮");
					}else if ("14".equals(arrSglx[j])){
						sglx.append("火药爆炸");
					}else if ("15".equals(arrSglx[j])){
						sglx.append("瓦斯爆炸");
					}else if ("16".equals(arrSglx[j])){
						sglx.append("锅炉爆炸");
					}else if ("17".equals(arrSglx[j])){
						sglx.append("容器爆炸");
					}else if ("18".equals(arrSglx[j])){
						sglx.append("其它爆炸");
					}else if ("19".equals(arrSglx[j])){
						sglx.append("中毒和窒息");
					}else if ("20".equals(arrSglx[j])){
						sglx.append("其它伤害");
					}
				}
				map.put("m11", sglx);
			}
		}
		}
		return list;
	}

	@Override
	public List<Map<String, Object>> getqylistapp(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql="SELECT TOP "+mapData.get("pageSize")+" * FROM ("+
				"select ROW_NUMBER() OVER (ORDER BY a.m1) AS RowNumber,a.* FROM (SELECT DISTINCT b.id,b.m1,b.m11,b.m11_1,b.m11_2,b.m11_3,b.m5,b.m6,(case b.M36 when '1' then '工贸' when '2' then '化工' end) as m36,(case when (b.M39= '1') then '是' else '否'end) m39,b.m19,b.m44 FROM dw_workapproval a left join bis_enterprise b on a.id1=b.id WHERE a.S3=0 and b.S3=0 "+content+") a"
				+" )  AS s WHERE RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) ";
		List<Map<String, Object>> list = findBySql(sql, null, Map.class);
		return list;
	}
}