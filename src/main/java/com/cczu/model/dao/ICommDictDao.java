package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.sys.system.entity.Dict;

public interface ICommDictDao {
	/**
     * 查询企业数据表格
     * @return
     */
	public List<Dict> dataList(Map<String, Object> mapData);

}
