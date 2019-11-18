package com.cczu.model.dao;

import com.cczu.model.entity.CarRecognitionEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 车牌识别Dao
 * @author 
 * @date 2019年11月18日
 */
@Repository("CarRecognitionEntityDao")
public class CarRecognitionEntityDao extends BaseDao<CarRecognitionEntity, Long> {

	public List<CarRecognitionEntity> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT top " + mapData.get("pageSize") 
                    + " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM car_recognition_entity a WHERE a.s3=0 "
					+ content + " ) as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<CarRecognitionEntity> list = findBySql(sql, null, CarRecognitionEntity.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(1) sum  FROM car_recognition_entity a WHERE a.s3=0" + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("in_out_time") != null && mapData.get("in_out_time") != "") {
			content = content + " AND a.in_out_time = '" + mapData.get("in_out_time") + "'";
		}
		if (mapData.get("car_code") != null && mapData.get("car_code") != "") {
			content = content + " AND a.car_code = '" + mapData.get("car_code") + "'";
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE car_recognition_entity SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
   
}
