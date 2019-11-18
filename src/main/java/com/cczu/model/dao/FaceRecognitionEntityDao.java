package com.cczu.model.dao;

import com.cczu.model.entity.FaceRecognitionEntity;
import com.cczu.util.dao.BaseDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 双向人脸识别Dao
 * @author 
 * @date 2019年11月18日
 */
@Repository("FaceRecognitionEntityDao")
public class FaceRecognitionEntityDao extends BaseDao<FaceRecognitionEntity, Long> {

	public List<FaceRecognitionEntity> dataGrid(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT top " + mapData.get("pageSize") 
                    + " * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY a.id desc) AS RowNumber,a.* FROM face_recognition_entity a WHERE a.s3=0 "
					+ content + " ) as a WHERE RowNumber > "
					+ mapData.get("pageSize") + "*(" + mapData.get("pageNo") + "-1) ";
		List<FaceRecognitionEntity> list = findBySql(sql, null, FaceRecognitionEntity.class);
		return list;
	}

	public int getTotalCount(Map<String, Object> mapData) {
		String content = content(mapData);
		String sql = " SELECT COUNT(1) sum  FROM face_recognition_entity a WHERE a.s3=0" + content;
		List<Object> list = findBySql(sql);
		return (int) list.get(0);
	}

	public String content(Map<String, Object> mapData) {
		String content = "";
		if (mapData.get("ryname") != null && mapData.get("ryname") != "") {
			content = content + " AND a.ryname = '" + mapData.get("ryname") + "'";
		}
		if (mapData.get("in_out_time") != null && mapData.get("in_out_time") != "") {
			content = content + " AND a.in_out_time = '" + mapData.get("in_out_time") + "'";
		}
		return content;
	}

	public void deleteInfo(Long id) {
		String sql = " UPDATE face_recognition_entity SET S3=1 WHERE ID=" + id;
		updateBySql(sql);
	}
   
}
