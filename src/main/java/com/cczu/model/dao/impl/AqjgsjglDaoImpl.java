package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.cczu.model.dao.IAqjgsjglDao;
import com.cczu.model.entity.AQJG_AccidentInforEntity;
import com.cczu.util.dao.BaseDao;

@Repository("AqjgsjglDao")
public class AqjgsjglDaoImpl extends BaseDao<AQJG_AccidentInforEntity, Long>
		implements IAqjgsjglDao {

	@Override
	public List<AQJG_AccidentInforEntity> dataGrid(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = content(mapData);
		String sql = " SELECT TOP "
				+ mapData.get("pageSize")
				+ " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ID DESC) AS RowNumber,*  FROM aqjg_accidentinfor ai where S3=0 "
				+ content + ") " + " as s where  RowNumber > "
				+ mapData.get("pageSize") + "*(" + mapData.get("pageNo")
				+ "-1) ";

		List<AQJG_AccidentInforEntity> list = findBySql(sql, null,
				AQJG_AccidentInforEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		// TODO Auto-generated method stub
		String content = this.content(mapData);
		String sql = "select count(1) from aqjg_accidentinfor where s3=0 "
				+ content;
		List<Object> list = this.findBySql(sql);
		return (int) list.get(0);
	}

	@Override
	public AQJG_AccidentInforEntity findInfoById(long id) {
		// TODO Auto-generated method stub

		// String sql="SELECT * FROM aqjg_accidentinfor WHERE S3=0 AND ID="+id;
		String sql = "SELECT m1,m2,m3,(case m4 when 'wtdj' then '物体打击' when 'zt' then '灼烫' when 'wsbz' then '瓦斯爆炸' when 'clsh' then '车辆伤害' when 'hz' then '火灾'when 'hybz' then '火药爆炸'when 'jxsh' then '机械伤害' when 'gczl' then '高处坠落'  when 'glbz' then '锅炉爆炸'when 'qzsh' then '起重伤害' when 'tt' then '坍塌'when 'rqbz' then '容器爆炸'when 'cd' then '触电'when 'mdpb' then '冒顶片帮'when 'qtbz' then '其他爆炸'when 'yn' then '淹溺'when 'ts' then '透水'when 'zdhzx' then '中毒和窒息'when 'fp' then '放炮'when 'qtsh' then '其他伤害' end ) m4,(case m5 when '0' then '特别重大事故' when '1' then '重大事故' when '2' then '较大事故' when '3' then '一般事故'  end) m5, * FROM aqjg_accidentinfor WHERE s3=0 AND id="
				+ id;
		List<AQJG_AccidentInforEntity> list = findBySql(sql, null,
				AQJG_AccidentInforEntity.class);
		return list.get(0);
	}

	@Override
	public AQJG_AccidentInforEntity findInfoById2(long id) {
		// TODO Auto-generated method stub
		String sql = "SELECT  * FROM aqjg_accidentinfor WHERE S3=0 AND id="
				+ id;
		List<AQJG_AccidentInforEntity> list = findBySql(sql, null,
				AQJG_AccidentInforEntity.class);
		return list.get(0);
	}

	@Override
	public int getCountEveryMonth(Map<String, Object> mapData) {
		String content = this.content(mapData);
		String sql = "SELECT  COUNT(1) FROM aqjg_accidentinfor WHERE S3=0 "
				+ content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}
	@Override
	public Object[] getMaxYearAndMinYear() {
		String sql = "SELECT Max(CONVERT(VARCHAR(4),M1,120)) max,Min(CONVERT(VARCHAR(4),M1,120))min  FROM aqjg_accidentinfor WHERE S3=0";
		List<Object[]>list=null;
		try {
			
			list=findBySql(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list.get(0);
	}

	@Override
	public void saveInfo(AQJG_AccidentInforEntity aie) {
		// TODO Auto-generated method stub
		save(aie);
	}

	@Override
	public void updateInfo(AQJG_AccidentInforEntity aie) {
		// TODO Auto-generated method stub
		System.out.println(aie.getID());
		save(aie);
	}

	@Override
	public void deleteInfo(long id) {
		// TODO Auto-generated method stub
		String sql = " UPDATE aqjg_accidentinfor SET S3=1 WHERE ID =" + id;
		updateBySql(sql);
	}

	/**
	 * 查询条件判断
	 * 
	 * @return
	 * @throws ParseException
	 */
	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("sgtype") != null && mapData.get("sgtype") != "") {
			content = content + "AND M4 ='" + mapData.get("sgtype") + "'";
		}
		if (mapData.get("sglevel") != null && mapData.get("sglevel") != "") {
			content = content + "AND M5 ='" + mapData.get("sglevel") + "'";
		}
		if (mapData.get("dwname") != null && mapData.get("dwname") != "") {
			content = content + "AND M2 like'%" + mapData.get("dwname") + "%'";
		}
		if (mapData.get("date") != null && mapData.get("date") != "") {
			content = content + "AND CONVERT(VARCHAR,M1,120) LIKE '"
					+ mapData.get("date") + "%'";
		}
		if (mapData.get("year") != null && mapData.get("year") != "") {
			content = content + "AND CONVERT(VARCHAR,M1,120) LIKE '"
					+ mapData.get("year") + "%'";
		}
		return content;
	}

}
