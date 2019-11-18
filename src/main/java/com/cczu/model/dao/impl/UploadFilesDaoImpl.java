package com.cczu.model.dao.impl;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cczu.model.entity.Upload_FilesEntity;
import com.cczu.model.dao.IUploadFilesDao;
import com.cczu.util.dao.BaseDao;

@Repository("UploadFilesDao")
public class UploadFilesDaoImpl extends BaseDao<Upload_FilesEntity, Long> implements IUploadFilesDao {

	@Override
	public List<Upload_FilesEntity> dataGrid(Map<String, Object> mapData) {
		String content=content(mapData);
		
		String sql=" SELECT TOP "+mapData.get("pageSize")+" * FROM ( SELECT ROW_NUMBER() OVER (ORDER BY ID) AS RowNumber,* FROM upload_files WHERE s3=0 "+content+") "
				+ "AS a WHERE S3=0 and RowNumber > "+mapData.get("pageSize")+"*("+mapData.get("pageNo")+"-1) "+ content ;
		
		List<Upload_FilesEntity> list=findBySql(sql, null,Upload_FilesEntity.class);
		return list;
	}

	@Override
	public int getTotalCount(Map<String, Object> mapData) {
		String content=content(mapData);
		String sql=" SELECT COUNT(*) SUM  FROM upload_files WHERE S3=0 AND 1=1"+ content;
		List<Object> list=findBySql(sql);
		return (int) list.get(0);
	}

	@Transactional(readOnly=false)
	public void saveInfo(Upload_FilesEntity file) {
		save(file);
	}

	/**
     * 查询条件判断
     * @return
	 * @throws ParseException 
     */
	public String content(Map<String, Object> mapData) {
		String content=" ";
		if(mapData.get("filename")!=null&&mapData.get("filename")!=""){
			content = content + "AND M1 like'%"+mapData.get("filename")+"%'"; 
		}
		if(mapData.get("filetype")!=null&&mapData.get("filetype")!=""){
			content = content + "AND M3 ike'%"+mapData.get("filetype")+"%'"; 
		}
		
		return content;
	}

	@Override
	public void deleteInfo(Upload_FilesEntity file) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Upload_FilesEntity findInfoById(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
