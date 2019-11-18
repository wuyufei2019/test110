package com.cczu.model.dao;

import java.util.List;
import java.util.Map;

import com.cczu.model.entity.AQPX_ItembankEntity;

public interface IAqpxStkxxDao {
	
	/**
	 * 添加试题库试题
	 * @param ai
	 */
	public void addInfo(AQPX_ItembankEntity ai);
	
	/**
	 * 修改
	 * @param ai
	 */
	public void updateInfo(AQPX_ItembankEntity ai);

	/**
	 * 删除
	 * @param id
	 */
	public void deleteInfo(Long id);
	
	/**
	 * 通过企业id查询所有试题库信息
	 * @param qyid
	 * @return
	 */
	public AQPX_ItembankEntity findAll(Long qyid);
	
	/**
	 * 通过课程id查询该课程所有的试题
	 * @param kcid
	 * @return
	 */
	public AQPX_ItembankEntity findkc(Long kcid);
	
	/**
	 * 出题
	 * @param kcid
	 * @return
	 */
	public List<AQPX_ItembankEntity> getst(Long kcid);
	
	/**
	 * 出单选题
	 * @param kcid
	 * @param dxsum
	 * @return
	 */
	public List<AQPX_ItembankEntity> getdx(Long kcid,int dxsum);
	
	/**
	 * 处多选题
	 * @param kcid
	 * @param dsxsum
	 * @return
	 */
	public List<AQPX_ItembankEntity> getdsx(Long kcid,int dsxsum);
	
	/**
	 * 出填空题
	 * @param kcid
	 * @param tksum
	 * @return
	 */
	public List<AQPX_ItembankEntity> gettk(Long kcid, int tksum);
	
	/**
	 * 出判断题
	 * @param kcid
	 * @param pdsum
	 * @return
	 */
	public List<AQPX_ItembankEntity> getpd(Long kcid, int pdsum);
	
	/**
	 * 出考题
	 * @return
	 */
	public List<AQPX_ItembankEntity> getkt(Long kcid, int dxsum, int dsxsum, int tksum, int pdsum);
	
	
	/**
	 * 通过试题id查询试题信息
	 * @param id
	 * @return
	 */
	public List<AQPX_ItembankEntity> getstxx(Long id);
	
	
	/**
	 * 分页查询
	 * @param mapData
	 * @param qyid
	 * @return
	 */
	public List<Map<String, Object>> dataGrid(Map<String,Object> mapData);
	
	/**查询数据条数
	 * @param mapData
	 * @return
	 */
	
	public int getTotalCount(Map<String, Object> mapData);

	/**
	 * 查询条件
	 * @param mapData
	 * @return
	 */
	public String content(Map<String, Object> mapData);
	
	/**
	 * 通过id查询信息
	 * @param id
	 * @return
	 */
	public AQPX_ItembankEntity findByid(Long id);
	
	/**
	 * 导出excel
	 * @param mapData
	 * @return
	 */
	public List<Object> getExport(Map<String, Object> mapData);

	/**
	 * 导出的条件2
	 * @param mapData
	 * @return
	 */
	public String content2(Map<String, Object> mapData);
	
	
	/**
	 * 通过企业id与m1 题目类型来 查询每种题目类型的分数
	 * @param m1
	 * @return
	 */
	public List<Map<String, Object>> findbym(Long id1, String m1);
	
	/**
	 * 根据课程id统计每个题型的数量
	 * @param kcid
	 * @return
	 */
	public List<Map<String, Object>> getStSum(Long kcid);
}
